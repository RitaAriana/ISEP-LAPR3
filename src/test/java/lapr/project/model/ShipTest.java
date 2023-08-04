package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ShipTest {

    @Test
    void setMMSI() {
        Ship ship = new Ship();
        ship.setMMSI("123456789");
        assertEquals("123456789", ship.getMMSI());
    }

    @Test
    void setMMSIWrong() {
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setMMSI("12345678");
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setMMSINull() {
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setMMSI(null);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setName() {
        Ship ship = new Ship();
        ship.setName("NavioBom");
        assertEquals("NavioBom", ship.getName());
    }

    @Test
    void setNameEmpty(){
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setName("");
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setNameWrong(){
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setName(null);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setShipID() {
        Ship ship = new Ship();
        ship.setShipID("IMO1234567");
        assertEquals("IMO1234567", ship.getShipID());
    }

    @Test
    void setShipIDNoIMO(){
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setShipID("INO1234567");
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);

    }

    @Test
    void setShipIDWrongLength(){
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setShipID("IMO123456");
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);

    }

    @Test
    void setShipIDNull(){
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setShipID(null);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);

    }

    @Test
    void setNumberGenerators() {
        Ship ship = new Ship();
        ship.setNumberGenerators(5);
        assertEquals(5, ship.getEnergyGenerators());
    }

    @Test
    void setNumberGeneratorsWrong(){
        boolean exception = false;
        Ship ship = new Ship();
        try{
            ship.setNumberGenerators(-4);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setGeneratorOutput() {
        Ship ship = new Ship();
        ship.setGeneratorOutput(18f);
        assertEquals(18f, ship.getGeneratorOutput(), 0);
    }

    @Test
    void setGeneratorOutputWrong() {
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setGeneratorOutput(-4f);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setCallSign() {
        Ship ship = new Ship();
        ship.setCallSign("callSign");
        assertEquals("callSign", ship.getCallSign());
    }

    @Test
    void setCallSignEmpty(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setCallSign("");
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setCallSignNull(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setCallSign(null);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setCargoNull(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setCargo(null);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setCargoEmpty() {
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setCargo("");
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setCargo(){
        Ship ship = new Ship();
        ship.setCargo("3");
        assertEquals("3", ship.getCargo());
    }


    @Test
    void setLength(){
        Ship ship = new Ship();

        ship.setLength(9f);

        assertEquals(9f, ship.getLength(), 0);
    }

    @Test
    void setLengthWrong(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setLength(-4f);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setLength0(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setLength(0f);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setWidth(){
        Ship ship = new Ship();
        ship.setWidth(9f);
        assertEquals(9f, ship.getWidth(), 0);
    }

    @Test
    void setWidthWrong(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setWidth(-2f);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setWidth0(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setWidth(0f);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setDraft(){
        Ship ship = new Ship();
        ship.setDraft(24f);
        assertEquals(24f, ship.getDraft(), 0);
    }

    @Test
    void setDraftWrong(){
        boolean exception = false;
        Ship ship = new Ship();

        try{
            ship.setDraft(-2f);
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    void setCapacity(){
        Ship ship = new Ship();
        ship.setLength(5f);
        ship.setWidth(2f);
        ship.setCapacity();

        assertEquals(10, ship.getCapacity(), 0);
    }

    @Test
    void getCapacity(){
        Ship ship = new Ship();
        ship.setLength(5f);
        ship.setWidth(2f);
        ship.setCapacity();

        assertEquals(10, ship.getCapacity(), 0);
    }

    @Test
    void setVesselType(){
        Ship ship = new Ship();
        ship.setVesselType(232);
        assertEquals(232, ship.getVesselType());
    }

    @Test
    void compareToEquals() {
        Ship ship = new Ship("123456789", "Navio", "IMO1234567", 3, 5f, "CHAMAMENTO", 583, 18f, 9f, "23", 9f, new ShipLocation());
        Ship ship2 = new Ship("123456789", "Navio", "IMO1234567", 3, 5f, "CHAMAMENTO", 583, 18f, 9f, "23", 9f, new ShipLocation());

        assertEquals(0, ship.compareTo(ship2));
    }

    @Test
    void compareToBigger() {
        Ship ship = new Ship("123456790", "Navio", "IMO1234567", 3, 5f, "CHAMAMENTO", 583, 18f, 9f, "23", 9f, new ShipLocation());
        Ship ship2 = new Ship("123456789", "Navio", "IMO1234567", 3, 5f, "CHAMAMENTO", 583, 18f, 9f, "23", 9f, new ShipLocation());

        assertEquals(1, ship.compareTo(ship2));
    }

    @Test
    void compareToSmaller() {
        Ship ship = new Ship("123456789", "Navio", "IMO1234567", 3, 5f, "CHAMAMENTO", 583, 18f, 9f, "23", 9f, new ShipLocation());
        Ship ship2 = new Ship("123456790", "Navio", "IMO1234567", 3, 5f, "CHAMAMENTO", 583, 18f, 9f, "23", 9f, new ShipLocation());

        assertEquals(-1, ship.compareTo(ship2));
    }
}