package lapr.project.model.esinf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void setWeight() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        edge.setWeight(5);
        assertEquals(5,edge.getWeight());
    }

    @Test
    void testToString() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        String esperado = "A -> B\nWeight: 10";
        assertTrue(esperado.equals(edge.toString()));

    }

    @Test
    void testEqualsSame() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        boolean result = edge.equals(edge);
        assertTrue(result);
    }

    @Test
    void testEqualsNull() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        Edge<String, Integer> test = null;
        boolean result = edge.equals(test);
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentClass() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        String test = null;
        boolean result = edge.equals(test);
        assertFalse(result);
    }

    @Test
    void testEquals() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        Edge<String, Integer> edge2 = new Edge<>("C","B",10);
        boolean result = edge.equals(edge2);
        assertFalse(result);
    }

    @Test
    void testHashCode() {
        Edge<String, Integer> edge = new Edge<>("A","B",10);
        assertEquals(3042,edge.hashCode());

    }
}