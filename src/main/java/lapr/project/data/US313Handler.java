package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Container;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.util.List;

public class US313Handler {

    private final WriteForAFile writeForAFile;

    private final TransferFromDataBase transferFromDataBase;

    public US313Handler(){
        transferFromDataBase = App.getInstance().getCompany().getTransferFromDatabase();
        writeForAFile = new WriteForAFile();
    }

    public void importContainers(){
        List<Container> containerLst = transferFromDataBase.importContainers(16);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("18,26,10\n");
        for(Container container : containerLst){
            stringBuilder.append(String.format("%s,%d,%d,%d", container.getNumber(), container.getX(), container.getY(), container.getZ()) + "\n");
        }

        String finalString = stringBuilder.toString();

        try{
            writeForAFile.writeForAFile(finalString, "containers2", new File(".\\docs\\US313"), false);
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
