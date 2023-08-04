package lapr.project.model.esinf;

import lapr.project.model.Ports;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class that represents a 2DTree
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 * @param <T> Generic Class T
 */
public class KDTree<T> {

    /* Nested static class for a 2D tree node. */

    public static class Node<T> {

        protected Point2D.Double coords;
        protected Node<T> left;
        protected Node<T> right;
        protected T object;

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param object
         * @param x
         * @param y
         */
        public Node(T object, double x, double y) {
            this.coords = new Point2D.Double(x,y);
            this.object = object;
        }

        // accessor methods
        public T getElement() { return object; }
        public Point2D.Double getCoords() { return coords; }
        public Double getX() { return coords.x; }
        public Double getY() { return coords.y; }
        public Node<T> getLeft() { return left; }
        public Node<T> getRight() { return right; }

        // update methods
        public void setElement(T object) { this.object = object; }
        public void setLeft(Node<T> leftChild) { left = leftChild; }
        public void setRight(Node<T> rightChild) { right = rightChild; }
        public void setCoords(Double latitude, Double longitude){ this.coords =new Point2D.Double(latitude,longitude); }
    }

    //----------- end of nested Node class -----------

    private final Comparator<Node<T>> compareX = new Comparator<Node<T>>() {
        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };

    private final Comparator<Node<T>> compareY = new Comparator<Node<T>>() {
        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

    public Node<T> root; // root of the tree

    /**
     * Constructs an empty 2D tree
     */
    public KDTree(){
        root=null;
    }

    /**
     *
     * @return root Node of the tree (or null if tree is empty)
     */
    public Node<T> root() {
        return root;
    }

    /**
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return root==null;
    }

    public void insert(T object, double x, double y) {
        Node<T> node = new Node<>(object, x, y);
        if (root == null)
            root = node;
        else
            insert(node, root, true);
    }

    private void insert(Node<T> node, Node<T> currentNode, boolean divX) {
        if (node == null)
            return;
        if (node.coords.equals(currentNode.coords))
            return;
        int compareResult = (divX ? compareX : compareY).compare(node, currentNode);
        if (compareResult == -1)
            if(currentNode.left == null)
                currentNode.left = node;
            else
                insert(node, currentNode.left, !divX);
        else
        if(currentNode.right == null)
            currentNode.right = node;
        else
            insert(node, currentNode.right, !divX);
    }

    public T findNearestNeighbour(double x, double y) {
        Node<T> closestsNode = new Node(null,Double.MAX_VALUE,Double.MAX_VALUE);
        return findNearestNeighbour(root, x, y,closestsNode, true);
    }

    private T findNearestNeighbour(Node<T> node, double x, double y, Node<T> closestNode , boolean divX) {
        if (node == null)
            return null;

        double d = Point2D.distanceSq(node.coords.x, node.coords.y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.coords.x, closestNode.coords.y, x, y);

        if (closestDist > d) {
            closestNode.setElement(node.getElement());
            closestNode.setLeft(node.getLeft());
            closestNode.setRight(node.getRight());
            closestNode.setCoords(node.getX(),node.getY());
        }

        double delta = divX ? x - node.coords.x : y - node.coords.y;
        double delta2 = delta * delta;

        Node<T> node1 = delta < 0 ? node.left : node.right;
        Node<T> node2 = delta < 0 ? node.right : node.left;

        findNearestNeighbour(node1,x,y,closestNode, !divX);

        if (delta2 < closestDist) {
            findNearestNeighbour(node2, x,y,closestNode,!divX);
        }

        return closestNode.getElement();
    }

    public KDTree(List<Node<T>> nodes) {
        root = null;
        balanceTree(nodes);

    }


    public void balanceTree(List<Node<T>> nodes) {
        root = balanceTree(true, nodes);
    }

    private Node<T> balanceTree(boolean divX, List<Node<T>> nodes) {
        if (nodes == null || nodes.isEmpty())
            return null;
        nodes.sort(divX ? compareX : compareY);
        int med = nodes.size() / 2;
        Node<T> node = new Node<>(nodes.get(med).getElement(), nodes.get(med).getX(), nodes.get(med).getY());
        node.left = balanceTree(!divX, nodes.subList(0, med));
        if (med + 1 <= nodes.size() - 1)
            node.right = balanceTree(!divX, nodes.subList(med+1, nodes.size()));
        insert(node.getElement(),node.getX(),node.getY());
        return node;
    }
}
