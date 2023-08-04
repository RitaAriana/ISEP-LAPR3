package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.*;

class ShipLocationTest {

    @Test
    void setMMSI() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMMSI("123456789");

        assertEquals("123456789", shipLocation.getMMSI());
    }

    @Test
    void setMMSINull(){
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setMMSI(null);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setMMSIWrong(){
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setMMSI("12345678");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setMessageTime() {
        ShipLocation shipLocation = new ShipLocation();
        Date date1 = new Date();
        Date dateExpected = new Date();
        shipLocation.setMessageTime(date1);
        assertEquals(dateExpected, shipLocation.getMessageTime());
    }

    @Test
    void setLatitudeNull() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLatitude(null);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLatitudeEmpty() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLatitude("");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLatitude91() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setLatitude("91");
        assertEquals("not available", shipLocation.getLatitude());
    }

    @Test
    void setLatitudeMinusLimit() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLatitude("-91");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLatitudeAfterLimit() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLatitude("93");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLatitudeCorrect(){
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setLatitude("47");
        assertEquals("47", shipLocation.getLatitude());
    }

    @Test
    void setLongitudeNull() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLongitude(null);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLongitudeEmpty(){
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLongitude("");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLongitude181() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setLongitude("181");
        assertEquals("not available", shipLocation.getLongitude());
    }

    @Test
    void setLongitudeMinusLimit() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLongitude("-183");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLongitudeAfterLimit() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setLongitude("183");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLongitudeCorrect() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setLongitude("47");
        assertEquals("47", shipLocation.getLongitude());
    }


    @Test
    void setSOGInvalid() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setSOG(-3f);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setSOGCorrect() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setSOG(9f);
        assertEquals(9f, shipLocation.getSOG(), 0);
    }

    @Test
    void setCOGMinus0() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setCOG(-20f);
        assertEquals(340f, shipLocation.getCOG(), 0);
    }

    @Test
    void setCOGMax360() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setCOG(420f);
        assertEquals(60f, shipLocation.getCOG(), 0);
    }

    @Test
    void setCOGNormal() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setCOG(30f);
        assertEquals(30f, shipLocation.getCOG(), 0);
    }

    @Test
    void setHeadingNull() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setHeading(null);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setHeadingEmpty(){
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setHeading("");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setHeading511() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setHeading("511");
        assertEquals("not available", shipLocation.getHeading());
    }

    @Test
    void setHeadingMinusLimit() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setHeading("-4");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setHeadingAfterLimit() {
        ShipLocation shipLocation = new ShipLocation();
        boolean result = false;

        try{
            shipLocation.setHeading("6345");
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setHeadingCorrect() {
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setHeading("47");
        assertEquals("47", shipLocation.getHeading());
    }

    @Test
    void setPosition(){
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setPosition("30");
        assertEquals("30", shipLocation.getPosition());
    }

    @Test
    void setTransceiverClass(){
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setTransceiverClass("A");
        assertEquals("A", shipLocation.getTransceiverClass());
    }

    @Test
    void compareTo() {
        ShipLocation shipLocation1 = new ShipLocation();
        shipLocation1.setMessageTime(new Date());
        ShipLocation shipLocation2 = shipLocation1;

        assertEquals(0, shipLocation1.compareTo(shipLocation2));
    }

    @Test
    void testEqualsObject() {
        ShipLocation shipLocation1 = new ShipLocation();
        shipLocation1.setMessageTime(new Date());
        Object objeto = shipLocation1;

        assertTrue(shipLocation1.equals(objeto));
    }

    @Test
    void testEqualsNull() {
        ShipLocation shipLocation1 = new ShipLocation();
        shipLocation1.setMessageTime(new Date());
        ShipLocation shipLocation2 = null;

        assertFalse(shipLocation1.equals(shipLocation2));
    }

    @Test
    void testEqualsDifferentClass(){
        ShipLocation shipLocation = new ShipLocation();
        Ship ship = new Ship();

        assertFalse(shipLocation.equals(ship));
    }

    @Test
    void testEqualsCorrect() {
        Date dateToUse = new Date();
        ShipLocation shipLocation1 = new ShipLocation("123456789", dateToUse, "56", "88", 32f, 44f, "330", "B" );
        ShipLocation shipLocation2 = new ShipLocation("123456789", dateToUse, "56", "88", 32f, 44f, "330", "B" );

        assertTrue(shipLocation1.equals(shipLocation2));
    }
}