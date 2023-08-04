package lapr.project.model;

import lapr.project.ui.Utils;
import lapr.project.utils.BSTInterface;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Class that represents a ShipLocationBST
 *
 * @author 1201239 Francisco Redol <1201239@isep.ipp.pt>
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */

public abstract class ShipLocationBST<E> implements BSTInterface<ShipLocation> {

    /** Nested static class for a binary search tree node. */

    protected static class Node<ShipLocation> {
        private ShipLocation shipLocation;          // a ShipLocation stored at this node
        private Node<ShipLocation> left;       // a reference to the left child (if any)
        private Node<ShipLocation> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param shipLocation  the element to be stored
         * @param leftChild   reference to a left child node
         * @param rightChild  reference to a right child node
         */
        public Node(ShipLocation shipLocation, Node<ShipLocation> leftChild, Node<ShipLocation> rightChild) {
            this.shipLocation = shipLocation;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public ShipLocation getShipLocation() { return shipLocation; }
        public Node<ShipLocation> getLeft() { return left; }
        public Node<ShipLocation> getRight() { return right; }

        // update methods
        public void setElement(ShipLocation shipLocation) { this.shipLocation = shipLocation; }
        public void setLeft(Node<ShipLocation> leftChild) { left = leftChild; }
        public void setRight(Node<ShipLocation> rightChild) { right = rightChild; }
    }

    //----------- end of nested Node class -----------

    protected Node<ShipLocation> root;     // root of the tree

    /* Constructs an empty binary search tree. */
    public ShipLocationBST() {
        root = null;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<ShipLocation> root() {
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return root==null;
    }

    /**
     * @param shipLocation that the user wants to search
     * @return null if the location is null. Otherwise, it will return the found ShipLocation.
     */
    public Node<ShipLocation> find(ShipLocation shipLocation){
        if(shipLocation == null)
            return null;
        return find(root, shipLocation);
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param shipLocation    the element to find
     * @return the Node that contains the Element, or null otherwise
     *
     * This method despite not being essential is very useful.
     * It is written here in order to be used by this class and its
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<ShipLocation> find(Node<ShipLocation> node, ShipLocation shipLocation){
        if (node == null)
            return null;
        if (node.getShipLocation().compareTo(shipLocation)==0)
            return node;
        if (node.getShipLocation().compareTo(shipLocation) > 0)
            return find(node.getLeft(),shipLocation);
        else
            return find(node.getRight(),shipLocation);
    }

    /*
     * Inserts an element in the tree.
     */
    public abstract void insert(ShipLocation shipLocation);

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size(){
        return size(root);
    }

    private int size(Node<ShipLocation> node){
        if(node == null)
            return 0;

        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    /*
     * Returns the height of the tree
     * @return height
     */
    public int height(){
        return height(root);
    }

    /*
     * Returns the height of the subtree rooted at Node node.
     * @param node A valid Node within the tree
     * @return height
     */
    protected int height(Node<ShipLocation> node){
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
     * @return the smallest element within the tree
     */
    public ShipLocation smallestElement(){
        return smallestElement(root);
    }

    protected ShipLocation smallestElement(Node<ShipLocation> node) {
        if (node == null)
            return null;

        Node<ShipLocation> auxNode;
        for(auxNode = node; auxNode.getLeft() !=  null; auxNode = auxNode.getLeft());

        return auxNode.getShipLocation();
    }

    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<ShipLocation> inOrder(){
        List<ShipLocation> snapshot = new ArrayList<>();
        if (root!=null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }
    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     * @param node       Node serving as the root of a subtree
     * @param snapshot  a list to which results are appended
     */
    private void inOrderSubtree(Node<ShipLocation> node, List<ShipLocation> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getShipLocation());
        inOrderSubtree(node.getRight(), snapshot);
    }

//#############################################

    /**
     * This method allows the user to search a certain ship location on the BST through its date
     * @param date that identifies the desired shipLocation
     * @return the identified ShipLocation
     */
    public ShipLocation getShipLocationByDate(Date date){
        ShipLocation shipLocationToFind = new ShipLocation();
        shipLocationToFind.setMessageTime(date);
        Node<ShipLocation> aux = find(root, shipLocationToFind);
        if(aux==null){
            return null;
        } else {
            return find(root, shipLocationToFind).getShipLocation();
        }
    }

    /**
     * This method allows to obtain the list of the ship's positional messages over a period of time
     * @param initialDate initial date of positional messages
     * @param finalDate final date of positional messages
     * @return list with the respective positional messages
     */
    public List<String> getPositionalMessages(Date initialDate, Date finalDate){
        List<ShipLocation> listDates= new ArrayList<>();
        List<String> positionalMessages = new ArrayList<>();

        getSpecificDatePeriod(root,initialDate,finalDate,listDates);

        for (ShipLocation aux : listDates){
            positionalMessages.add(aux.toString());
        }

        return positionalMessages;
    }

    /**
     * This method allows to obtain the ship's positional message over a date
     * @param date date of positional message
     * @return respective positional message
     */
    public String getPositionalMessages(Date date){
        ShipLocation location=getShipLocationByDate(date);
        if(location!=null){
            return location.toString();
        } else {
            return null;
        }
    }

    public void getSpecificDatePeriod(Node<ShipLocation> node, Date initialDate, Date finalDate, List<ShipLocation> listDates) {

        if(node == null){
            return ;
        }
        if(node.getShipLocation().getMessageTime().compareTo(initialDate)>0){
            getSpecificDatePeriod(node.getLeft(),initialDate,finalDate,listDates);
        }

        if(node.getShipLocation().getMessageTime().compareTo(initialDate)>=0 && node.getShipLocation().getMessageTime().compareTo(finalDate)<=0){
           listDates.add(node.getShipLocation());
        }

        getSpecificDatePeriod(node.getRight(),initialDate,finalDate,listDates);

    }

    /**
     * returns to first ship location
     * @return returns to first ship location
     */
    private ShipLocation startShipLocation(){
        Iterator<ShipLocation> shipLocations = inOrder().iterator();
        return shipLocations.next();
    }

    /**
     * returns to last ship location
     * @return returns to last ship location
     */
    private ShipLocation endShipLocation(){
        Iterator<ShipLocation> shipLocations = inOrder().iterator();
        ShipLocation shipLocation = null;
        while (shipLocations.hasNext()){
            shipLocation = shipLocations.next();
        }
        return shipLocation;
    }

    /**
     * returns date of first ship location
     * @return returns date of first ship location
     */
    public Date getStartBase() {
        return startShipLocation().getMessageTime();
    }

    /**
     * returns date of last ship location
     * @return returns date of last ship location
     */
    public Date getEndBase() {
        return endShipLocation().getMessageTime();
    }

    /**
     * returns the total number of moves made by a ship in a voyage
     * @return returns the total number of moves made by a ship in a voyage
     */
    public int getTotalMovements() {
        return this.size()-1;
    }

    /**
     * returns the total time spent on a voyage taken by a ship
     * @return returns the total time spent on a voyage taken by a ship
     */
    public String getTotalMovementsTime() {
        Iterator<ShipLocation> shipLocationIterator = inOrder().iterator();
        double sum = 0;
        long date1 = 0, date2 = 0;
        ShipLocation firstLocation = shipLocationIterator.next();
        while (shipLocationIterator.hasNext()){
            ShipLocation secondLocation = shipLocationIterator.next();
            date1 = firstLocation.getMessageTime().getTime();
            date2 = secondLocation.getMessageTime().getTime();

            sum += Math.abs(date1-date2);

            firstLocation = secondLocation;
        }
        return transformInHours(sum);
    }

    /**
     * turns time in seconds into hours, minutes and seconds
     * @param totalTime the total time in seconds
     * @return a String with the following format: HH:MM:SS
     */
    private String transformInHours(double totalTime){
        DecimalFormat decimalFormat = new DecimalFormat("00");
        int hour = (int)totalTime/3600000;
        totalTime%=3600;
        int minutes = (int) totalTime/60;
        totalTime%=60;
        int seconds = (int) totalTime;

        return decimalFormat.format(hour) + ":" + decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);
    }

    /**
     * returns to the highest ground speed reached by ship
     * @return the highest ground speed reached by ship
     */
    public double getMaximumSog() {
        Iterable<ShipLocation> shipLocations = inOrder();
        double maxSog = 0;
        for (ShipLocation s : shipLocations){
            if (s.getSOG() > maxSog) maxSog = s.getSOG();
        }
        return maxSog;
    }

    /**
     * returns to the average speed over ground reached by ship
     * @return the average speed over ground reached by ship
     */
    public double getMeanSog() {
        Iterable<ShipLocation> shipLocations = inOrder();
        double meanSog = 0, size=0;
        for (ShipLocation s : shipLocations){
            meanSog+=s.getSOG();
            size++;
        }
        if (size == 0) return 0;
        else return meanSog/size();
    }

    /**
     * returns the longest course on the ground hit by a ship
     * @return the longest course on the ground hit by a ship
     */
    public double getMaximumCog() {
        Iterable<ShipLocation> shipLocations = inOrder();
        double maxCog = 0;
        for (ShipLocation s : shipLocations){
            if (s.getCOG() > maxCog) maxCog = s.getCOG();
        }
        return maxCog;
    }

    /**
     * returns the middle course over the ground hit by a ship
     * @return the middle course over the ground hit by a ship
     */
    public double getMeanCog() {
        Iterable<ShipLocation> shipLocations = inOrder();
        double meanCog = 0;
        for (ShipLocation s : shipLocations){
            meanCog+=s.getCOG();
        }
        if (size() == 0) return 0;
        else return meanCog/size();

    }

    /**
     returns the latitude of the departure port of the voyage made by the ship
     * @return the latitude of the departure port of the voyage made by the ship
     */
    public String getLatitudeDeparture() {

        return startShipLocation().getLatitude();
    }

    /**
     * returns the longitude of the departure port of the voyage made by the ship
     * @return the longitude of the departure port of the voyage made by the ship
     */
    public String getLongitudeDeparture() {

        return startShipLocation().getLongitude();
    }

    /**
     * returns the latitude of the port of arrival of the voyage made by the ship
     * @return the latitude of the port of arrival of the voyage made by the ship
     */
    public String getArrivalLatitude() {
        return endShipLocation().getLatitude();
    }

    /**
     * returns the longitude of the port of arrival of the voyage made by the ship
     * @return the longitude of the port of arrival of the voyage made by the ship
     */
    public String getArrivalLongitude() {
        return endShipLocation().getLongitude();
    }

    /**
     * returns the total distance traveled by a ship on a voyage
     * @return the total distance traveled by a ship on a voyage
     */
    public double getTravelledDistance() {
        Iterator<ShipLocation> shipLocationIterator = inOrder().iterator();
        double sum = 0;
        ShipLocation firstLocation = shipLocationIterator.next();
        while (shipLocationIterator.hasNext()){
            ShipLocation secondLocation = shipLocationIterator.next();

            if (firstLocation.getLatitude().equals("not available") || firstLocation.getLongitude().equals("not available") || secondLocation.getLatitude().equals("not available") || secondLocation.getLongitude().equals("not available") || size() < 2){
                sum += 0;
            }
            else
            {
                sum += Utils.calculateDistance(Double.parseDouble(firstLocation.getLatitude()), Double.parseDouble(firstLocation.getLongitude()), Double.parseDouble(secondLocation.getLatitude()), Double.parseDouble(secondLocation.getLongitude()));
            }

            firstLocation = secondLocation;
        }
        return sum;
    }

    /**
     * returns the delta distance traveled by ship, that is, the distance between the departure point and the arrival point of the voyage
     * @return the delta distance traveled by ship
     */
    public double getDeltaDistance() {
        if (getArrivalLongitude().equals("not available") || getArrivalLatitude().equals("not available") || getLongitudeDeparture().equals("not available") || getLatitudeDeparture().equals("not available") || size() < 2){
            return 0;
        }
       else return Utils.calculateDistance(Double.parseDouble(getLatitudeDeparture()), Double.parseDouble(getLongitudeDeparture()), Double.parseDouble(getArrivalLatitude()), Double.parseDouble(getArrivalLongitude()));
    }

    /*
    public double calculateDistance(double departureLatitude, double departureLongitude, double arrivalLatitude, double arrivalLongitude){

        int earthRadius = 6371;

        double initialLatitude= departureLatitude * Math.PI/180;
        double initialLongitude= arrivalLatitude * Math.PI/180;


        double variationOfLatitude = Math.abs(arrivalLatitude - departureLatitude) * Math.PI/180;

        double variationOfLongitude = Math.abs(arrivalLongitude - departureLongitude) * Math.PI/180;

        double a = Math.sin(variationOfLatitude/2) * Math.sin(variationOfLatitude/2) + Math.cos(initialLatitude)* Math.cos(initialLongitude) * Math.sin(variationOfLongitude/2) * Math.sin(variationOfLongitude/2);

        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));


        return (earthRadius*b);
    }
    */



} //----------- end of BST class -----------


