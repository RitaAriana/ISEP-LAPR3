package lapr.project.model;

import lapr.project.ui.Utils;
import lapr.project.utils.BSTInterface;

import java.util.*;

/**
 * Class that represents a BstShip
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public abstract class BstShip<E> implements BSTInterface<Ship> {

    protected Node<Ship> root = null;     // root of the tree

    //----------- end of nested Node class -----------

    /**
     * Constructs an empty binary search tree
     */
    public BstShip() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empsty)
     */
    protected Node<Ship> root() {
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @param ship That is being searched
     * @return null if the ship was not found. Otherwise, the node containing the ship.
     */
    public Node<Ship> find(Ship ship) {
        if (ship == null)
            return null;
        return find(root, ship);
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param ship the element to find
     * @return the Node that contains the Element, or null otherwise
     * <p>
     * This method despite not being essential is very useful.
     * It is written here in order to be used by this class and its
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<Ship> find(Node<Ship> node, Ship ship) {
        if (node == null)
            return null;
        if (node.getShip().compareTo(ship) == 0)
            return node;
        if (node.getShip().compareTo(ship) > 0)
            return find(node.getLeft(), ship);
        else
            return find(node.getRight(), ship);
    }

    /*
     * Inserts an element in the tree.
     */
    public abstract void insert(Ship ship);

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node<Ship> node) {
        if (node == null)
            return 0;

        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    public int height() {
        return height(root);
    }

    /*
     * Returns the height of the subtree rooted at Node node.
     * @param node A valid Node within the tree
     * @return height
     */
    protected int height(Node<Ship> node) {
        if (node == null)
            return -1;

        int lDepth = height(node.left);
        int rDepth = height(node.right);

        if (lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }

    /**
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    public Ship smallestElement() {
        return smallestElement(root);
    }

    protected Ship smallestElement(Node<Ship> node) {
        if (node == null)
            return null;

        Node<Ship> auxNode;
        for (auxNode = node; auxNode.getLeft() != null; auxNode = auxNode.getLeft()) ;

        return auxNode.getShip();
    }

    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<Ship> inOrder() {
        List<Ship> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<Ship> node, List<Ship> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getShip());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * This method allows the user to search a certain ship on the BST through its MMSI code (unique code).
     *
     * @param MMSI that identifies the desired ship.
     * @return the identified Ship
     */
    public Ship getShipByMmsiCode(String MMSI) {
        Ship shipToFind = new Ship();
        shipToFind.setMMSI(MMSI);
        Node<Ship> aux = find(root, shipToFind);
        if (aux == null) {
            return null;
        } else {
            return find(root, shipToFind).getShip();
        }
    }

    /**
     * This method allows the user to search a certain ship on the BST through its CallSign
     * @param callSign that identifies the desired ship.
     * @return the identified Ship
     */
    public Ship getShipByCallSign(String callSign){
        Iterable<Ship> ships = inOrder();
        for (Ship s : ships){
            if (Objects.equals(s.getCallSign(), callSign)){
                return  s;
            }
        }
        return null;
    }

    //############ Methods Implemented ###################

    public List<TreeMap<Double, String>> getIntendedPairsOfShips() {
        List<TreeMap<Double, String>> listPairsOfShips = new ArrayList<>();
        List<Ship> bstInOrder = (List<Ship>) inOrder();


        for (int i = 0; i < size() - 1; i++) {
            TreeMap<Double, String> infoPair = new TreeMap<>(Collections.reverseOrder());
            Ship ship1 = bstInOrder.get(i);
            ShipLocationBST shipLocationBST = ship1.getShipPosition();
            Double travelledDistance = shipLocationBST.getTravelledDistance();

            if (travelledDistance > 10) {

                for (int j = i + 1; j < size(); j++) {
                    ShipLocationBST shipLocationBST2 = bstInOrder.get(j).getShipPosition();
                    Double travelledDistance2 = shipLocationBST2.getTravelledDistance();

                    if (travelledDistance2 > 10 && travelledDistance != travelledDistance2) {
                        String arrivalLat = shipLocationBST.getArrivalLatitude();
                        String arrivalLog = shipLocationBST.getArrivalLongitude();
                        String arrivalLat2 = shipLocationBST2.getArrivalLatitude();
                        String arrivalLog2 = shipLocationBST2.getArrivalLongitude();
                        if (arrivalLat.equals("not defined") || arrivalLog.equals("not defined") || arrivalLat2.equals("not defined") || arrivalLog2.equals("not defined")) {

                        } else {
                            Double arrivalDistance = Utils.calculateDistance(Double.parseDouble(arrivalLat), Double.parseDouble(arrivalLog), Double.parseDouble(arrivalLat2), Double.parseDouble(arrivalLog2));
                            if (arrivalDistance < 5) {
                                String depLat = shipLocationBST.getLatitudeDeparture();
                                String depLog = shipLocationBST.getLongitudeDeparture();
                                String depLat2 = shipLocationBST2.getLatitudeDeparture();
                                String depLog2 = shipLocationBST2.getLongitudeDeparture();
                                if (depLat.equals("not defined") || depLog.equals("not defined") || depLat2.equals("not defined") || depLog2.equals("not defined")) {

                                } else {
                                    Double depDistance = Utils.calculateDistance(Double.parseDouble(arrivalLat), Double.parseDouble(arrivalLog), Double.parseDouble(arrivalLat2), Double.parseDouble(arrivalLog2));
                                    if (depDistance < 5) {
                                        Double travelDistanceDifference = Math.abs(travelledDistance2 - travelledDistance);
                                        String stringWithAllInfo = String.format(" %s  %s", ship1.getMMSI(), bstInOrder.get(j).getMMSI());
                                        infoPair.put(travelDistanceDifference, stringWithAllInfo);
                                    }
                                }
                            }
                        }
                    }
                }
                listPairsOfShips.add(infoPair);
            }

        }
        return listPairsOfShips;
    }

    /**
     * Method that returns the top-N ships contained in a MAP
     *
     * @param numberShips Number of top N ships
     * @param initialDate The initial Time, in order to define the time window
     * @param finalDate   The final Time, in order to define the time window
     * @return a Map organizing ships by vessel
     */
    public Map<Integer, List<Summary>> getTopNShips(int numberShips, Date initialDate, Date finalDate) {
        Iterator<Ship> shipList = inOrder().iterator();
        Map<Integer, List<Summary>> summaryMap = new HashMap<>();

        while (shipList.hasNext()) {
            Ship toBeAdded = shipList.next();
            Summary summary = new Summary(toBeAdded);
            if (summary.getStartBaseDate().after(initialDate) && summary.getEndBaseDate().before(finalDate)) {
                if (summaryMap.get(toBeAdded.getVesselType()) == null)
                    summaryMap.put(toBeAdded.getVesselType(), new ArrayList<>());
                summaryMap.get(toBeAdded.getVesselType()).add(summary);
            }
        }

        for (Integer key : summaryMap.keySet()) {
            summaryMap.get(key).sort(new Comparator<Summary>() {
                @Override
                public int compare(Summary o1, Summary o2) {
                    if (o1.getTravelledDistance() > o2.getTravelledDistance())
                        return 1;
                    else if (o1.getTravelledDistance() < o2.getTravelledDistance())
                        return -1;
                    else
                        return 0;
                }
            });
            List<Summary> keyList = summaryMap.get(key);
            if (numberShips < keyList.size())
                summaryMap.replace(key, keyList.subList((keyList.size() - numberShips), keyList.size()));
            else
                summaryMap.replace(key, keyList);
        }

        return summaryMap;
    }

    /**
     * Nested static class for a binary search tree node.
     */

    protected static class Node<Ship> {
        private Ship ship;             // a Ship stored at this node
        private Node<Ship> left;       // a reference to the left child (if any)
        private Node<Ship> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param ship       the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(Ship ship, Node<Ship> leftChild, Node<Ship> rightChild) {
            this.ship = ship;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public Ship getShip() {
            return ship;
        }

        public Node<Ship> getLeft() {
            return left;
        }

        public void setLeft(Node<Ship> leftChild) {
            left = leftChild;
        }

        public Node<Ship> getRight() {
            return right;
        }

        public void setRight(Node<Ship> rightChild) {
            right = rightChild;
        }

        // update methods
        public void setElement(Ship ship) {
            this.ship = ship;
        }
    }
}