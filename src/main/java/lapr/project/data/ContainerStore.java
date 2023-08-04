package lapr.project.data;

import lapr.project.model.Container;

import java.util.ArrayList;
import java.util.List;

public class ContainerStore {

    private final List<Container> containers;
    private  int maxX;
    private  int maxY;
    private  int maxZ;



    public ContainerStore(){
        containers =new ArrayList<>();

        maxX = 0;
        maxY = 0;
        maxZ = 0;
    }

    public void setX(int x){
        this.maxX = x;
    }
    public void setY(int y){
        this.maxY = y;
    }
    public void setZ(int z){
        this.maxZ = z;
    }

    public List<Container> getContainers(){
        return containers;
    }

    public void saveContainer(Container container) {
        if (!containers.contains(container)){
            containers.add(container);
        }

    }

    public Container getContainerByNumber(String number){

        for (Container c : containers){
            if (c.getNumber().equals(number)) return c;
        }

        return null;
    }

    public Container getContainerByIsoCode(String number){

        for (Container c : containers){
            if (c.getIsoCode().equals(number)) return c;
        }

        return null;
    }

    public int[][][] matrixOfPositions(){
        int[][][] containersPositions = new int[maxX][maxY][maxZ];
        for (Container c : containers){
            containersPositions[c.getX()][c.getY()][c.getZ()] = 1;
        }
        return  containersPositions;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public Container getContainerAtAPosition(int x, int y, int z){
        for (Container c : containers){
            if (c.getX() == x && c.getY() == y && c.getZ() == z ) return c;
        }
        return null;
    }


}
