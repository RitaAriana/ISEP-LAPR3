package lapr.project.model;

import lapr.project.model.esinf.KDTree;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ports2DTreeTest {

    Ports2DTree portsEmpty = new Ports2DTree();
    Ports2DTree ports = new Ports2DTree();

    Ports port1;
    Ports port2;
    Ports port3;

    public Ports2DTreeTest(){
        Country country1=new Country("Europe","Denmark");
        PlaceLocation placeLocation1= new PlaceLocation(56.15,10.21666667);
        port1 = new Ports(country1,10358,"Aarhus",placeLocation1);
        ports.insert(port1,port1.getLatitude(),port1.getLongitude());
        PlaceLocation placeLocation2= new PlaceLocation(55.7,12.61666667);
        port2 = new Ports(country1,10358,"Aarhus",placeLocation2);
        ports.insert(port2,port2.getLatitude(),port2.getLongitude());
        Country country3=new Country("Europe","Russia");
        PlaceLocation placeLocation3= new PlaceLocation(59.88333333,30.21666667);
        port3 = new Ports(country3,18454,"St Petersburg",placeLocation3);
        ports.insert(port3,port3.getLatitude(),port3.getLongitude());
    }

    @Test
    void isEmpty() {
        assertTrue(portsEmpty.isEmpty());
    }

    @Test
    void isNotEmpty() {
        assertFalse(ports.isEmpty());
    }

    @Test
    void rootNull(){
        assertNull(portsEmpty.root());
    }

    @Test
    void insert01() {
        Ports result = ports.findNearestNeighbour(55.7, 12.61666667);
        assertEquals(port2, result);
    }

    @Test
    void insert02() {
        Ports result =ports.findNearestNeighbour(56.15, 10.21666667);
        assertEquals(port1, result);
    }

    @Test
    void insert03() {
        Ports result = ports.findNearestNeighbour(59.88333333, 30.21666667);
        assertEquals(port3, result);
    }

    @Test
    void insert04() {
        Country country=new Country("Europe","Denmark");
        PlaceLocation placeLocation= new PlaceLocation(0,0);
        Ports obj1 = new Ports(country, 12345,"Piran",placeLocation);
        ports.insert(obj1,obj1.getLatitude(),obj1.getLongitude());
        Ports result1 =  ports.findNearestNeighbour(0, 0);
        assertEquals(obj1, result1);
        PlaceLocation placeLocation1= new PlaceLocation(10,10);
        Ports obj2 = new Ports(country,23456,"Aspropyrgos",placeLocation1);
        ports.insert(obj2,obj2.getLatitude(),obj2.getLongitude());
        Ports result2 =  ports.findNearestNeighbour(10, 10);
        assertEquals(obj2, result2);
        PlaceLocation placeLocation2= new PlaceLocation(0,10);
        Ports obj3 = new Ports(country,34567,"San Vicente",placeLocation2);
        ports.insert(obj3,obj3.getLatitude(),obj3.getLongitude());
        Ports result3 =ports.findNearestNeighbour(0, 10);
        assertEquals(obj3, result3);
        PlaceLocation placeLocation3= new PlaceLocation(10,0);
        Ports obj4 = new Ports(country,45678,"Cartagena",placeLocation3);
        ports.insert(obj4,obj4.getLatitude(),obj4.getLongitude());
        Ports result4 =  ports.findNearestNeighbour(10, 0);
        assertEquals(obj4, result4);
        PlaceLocation placeLocation4= new PlaceLocation(20,20);
        Ports obj5 = new Ports(country,56789,"Santos",placeLocation4);
        ports.insert(obj5,obj5.getLatitude(),obj5.getLongitude());
        Ports result5 = ports.findNearestNeighbour(20, 20);
        assertEquals(obj5, result5);
    }

    @Test
    void balanceTree() {
        List<KDTree.Node<Ports>> lst = new ArrayList<>();
        Country country=new Country("Europe","Denmark");
        PlaceLocation placeLocation= new PlaceLocation(0,0);
        Ports obj1 = new Ports(country, 12345,"Piran",placeLocation);
        lst.add(new Ports2DTree.Node(obj1,obj1.getLatitude(),obj1.getLongitude()));
        PlaceLocation placeLocation1= new PlaceLocation(10,10);
        Ports obj2 = new Ports(country,23456,"Aspropyrgos",placeLocation1);
        lst.add(new Ports2DTree.Node(obj2,obj2.getLatitude(),obj2.getLongitude()));
        PlaceLocation placeLocation2= new PlaceLocation(0,10);
        Ports obj3 = new Ports(country,34567,"San Vicente",placeLocation2);
        lst.add(new Ports2DTree.Node(obj3,obj3.getLatitude(),obj3.getLongitude()));
        PlaceLocation placeLocation3= new PlaceLocation(11,0);
        Ports obj4 = new Ports(country,45678,"Cartagena",placeLocation3);
        lst.add(new Ports2DTree.Node(obj4,obj4.getLatitude(),obj4.getLongitude()));
        PlaceLocation placeLocation4= new PlaceLocation(20,20);
        Ports obj5 = new Ports(country,56789,"Santos",placeLocation4);
        lst.add(new Ports2DTree.Node(obj5,obj5.getLatitude(),obj5.getLongitude()));

        Ports2DTree ports2DTree = new Ports2DTree();
        ports2DTree.balanceTree(lst);

        Assert.assertEquals(obj2.getLatitude(),ports2DTree.root().getCoords().getX(),0);
    }
}