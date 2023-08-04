package lapr.project.model;

/**
 * ShipLocationAVL, which is a ShipLocationBST, but perfectly balanced
 *
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class ShipLocationAVL extends ShipLocationBST<ShipLocation>{

    private int balanceFactor(Node<ShipLocation> node){
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<ShipLocation> rightRotation(Node<ShipLocation> node){
        Node<ShipLocation> leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        return leftson;
    }

    private Node<ShipLocation> leftRotation(Node<ShipLocation> node){
        Node<ShipLocation> rightson = node.getRight();
        node.setRight(rightson.getLeft());
        rightson.setLeft(node);
        return rightson;
    }

    private Node<ShipLocation> twoRotations(Node<ShipLocation> node){
        if(balanceFactor(node) < -1) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        }
        if(balanceFactor(node) > 1) {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;

    }

    private Node<ShipLocation> balanceNodeShipLocation(Node<ShipLocation> node) {
        if(balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if(balanceFactor(node) > 1) {
            if(balanceFactor(node.getRight()) >= 0)
                node = leftRotation(node);
            else
                node = twoRotations(node);
        }
        return node;

    }

    @Override
    public void insert(ShipLocation element){
        if(element == null)
            return;
        root = insert(element, root);
    }

    private Node<ShipLocation> insert(ShipLocation element, Node<ShipLocation> node){
        if(node == null)
            return new Node(element, null, null);

        if(element.compareTo(node.getShipLocation()) == 0) {
            node.setElement(element);
        } else {
            if(node.getShipLocation().compareTo(element) > 0) {
                node.setLeft(insert(element, node.getLeft()));
                node = balanceNodeShipLocation(node);
            } else {
                node.setRight(insert(element, node.getRight()));
                node = balanceNodeShipLocation(node);
            }
        }
        return node;

    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        ShipLocationAVL second = (ShipLocationAVL) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<ShipLocation> root1, Node<ShipLocation> root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null) {
            if (root1.getShipLocation().compareTo(root2.getShipLocation()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else
                return false;
        }
        else
            return false;
    }

}