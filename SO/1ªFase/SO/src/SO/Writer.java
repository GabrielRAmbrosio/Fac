package recursoso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {
    
    private final String path = System.getProperty("user.dir") + "\\src\\Output\\";
    private File ajkpa, ajkpbfa;
    
    public Writer() throws IOException{
        createFile();
    }
    private void createFile() throws IOException{//creates two files, for each algorithm
        ajkpa = new File(path + "ajkpa.txt");
        ajkpbfa = new File(path + "ajkpbfa.txt");

        if(!ajkpa.exists()){//writes the header in both
            writeToFile("ajkpa", "filename\talg\ttt\titer\t\tbest");
            writeToFile("ajkpa", "----------------------------------------------------");
            
            writeToFile("ajkpbfa", "filename\talg\ttt\titer\t\tbest");
            writeToFile("ajkpbfa", "----------------------------------------------------");
        }
    }
    
    //writes a given line in a file
    public void writeToFile(String filename, String line) throws FileNotFoundException, IOException{
        if(filename.equals("ajkpa")){
            FileOutputStream fos = new FileOutputStream(ajkpa, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            bw.write(line);
            bw.newLine();
            bw.close();
        }else{
            FileOutputStream fos = new FileOutputStream(ajkpbfa, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            bw.write(line);
            bw.newLine();
            bw.close();
        }
    }

}
