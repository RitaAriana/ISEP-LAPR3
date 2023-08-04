package lapr.project.model.esinf;

import java.util.Objects;

/**
 *
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 * @author 1201239 Francisco Redol
 */
public class Edge<V,E> {

    /**
     * vertex origin
     */
    final private V vOrig;

    /**
     * vertex destination
     */
    final private V vDest;

    /**
     * Edge weight
     */
    private E weight;


    /**
     *
     * @param vOrig
     * @param vDest
     * @param weight
     */
    public Edge(V vOrig, V vDest, E weight) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public V getVOrig() {
        return vOrig;
    }

    /**
     *
     * @return
     */
    public V getVDest() {
        return vDest;
    }

    /**
     *
     * @return
     */
    public E getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(E weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s\nWeight: %s", vOrig, vDest, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge<V, E> edge = (Edge<V, E>) obj;
        return  vOrig.equals(edge.vOrig) &&
                vDest.equals(edge.vDest);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vOrig, vDest);

    }

}
