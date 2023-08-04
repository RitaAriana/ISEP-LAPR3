package lapr.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteForAFile {
    /**
     * Write the summary of a ship's movement in a file
     * @param string the text that will be written in the file
     * @param fileName the mmsi code of the ship to identify the summary
     * @return false if the operation fail, true otherwise
     * @throws IOException if some exception occur
     */
    public boolean writeForAFile(String string, String fileName, File file, boolean append) throws IOException {
        boolean flag = true;


        if (!file.exists()) {
            file.mkdirs();
        }

        if (string != null){
            File arch = new File(file + "\\" + fileName +".csv");
            if (arch.exists() && !append) arch.delete();
            arch.setWritable(true);
            FileWriter fw = new FileWriter(arch, true);

            try {

                arch.delete();
                fw.write(string);
            } catch (IOException e){
                flag = false;
                e.printStackTrace();
            } finally {
                fw.close();
                arch.setReadOnly();
            }
        }else
            flag = false;

        return flag;
    }
}
