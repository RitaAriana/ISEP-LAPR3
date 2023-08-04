package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class PlaceLocationTest {
    PlaceLocation location;

    public PlaceLocationTest(){
       location = new PlaceLocation(56.15,10.21666667);
    }

    @Test
    void getLatitude() {
        double expected = 56.15;
        assertEquals(expected,location.getLatitude(),0);
    }

    @Test
    void getLongitude() {
        double expected = 10.21666667;
        assertEquals(expected,location.getLongitude(),0);
    }

    @Test
    void setLatitudeAfterLimit() {
        PlaceLocation placeLocation= new PlaceLocation(54,120);
        boolean result = false;

        try{
            placeLocation.setLatitude(93);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLatitudeLimit() {
        PlaceLocation placeLocation= new PlaceLocation(54,120);
        boolean result = false;

        try{
            placeLocation.setLatitude(-93);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLatitudeCorrect(){
        PlaceLocation placeLocation = new PlaceLocation(54,120);
        placeLocation.setLatitude(90);
        assertEquals(90, placeLocation.getLatitude(),0);
    }

    @Test
    void setLatitudeCorrect01(){
        PlaceLocation placeLocation = new PlaceLocation(54,120);
        placeLocation.setLatitude(-90);
        assertEquals(-90, placeLocation.getLatitude(),0);
    }



    @Test
    void setLongitudeMinusLimit() {
        PlaceLocation placeLocation = new PlaceLocation(54,120);
        boolean result = false;

        try{
            placeLocation.setLongitude(-183);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLongitudeAfterLimit() {
        PlaceLocation placeLocation = new PlaceLocation(54,120);
        boolean result = false;

        try{
            placeLocation.setLongitude(183);
        }catch (Exception e){
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void setLongitudeCorrect() {
        PlaceLocation placeLocation = new PlaceLocation(54,120);
        placeLocation.setLongitude(180);
        assertEquals(180, placeLocation.getLongitude(),0);
    }

    @Test
    void setLongitudeCorrect01() {
        PlaceLocation placeLocation = new PlaceLocation(54,120);
        placeLocation.setLongitude(-180);
        assertEquals(-180, placeLocation.getLongitude(),0);
    }

}