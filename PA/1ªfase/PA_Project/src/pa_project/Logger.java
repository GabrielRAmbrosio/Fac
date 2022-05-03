package pa_project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public class Logger {
    private ArrayList<String> moves;
    private Date dateStart;
    
    public static final String LOGFILENAME = "logger.txt";
    
    public Logger(){
        moves = new ArrayList<>();
        dateStart = new Date();
    }
    
    public void eraseLog(){
        moves = null;
        dateStart = null;
    }
    
    public void addMove(String move){
        moves.add(move);
    }
    
    public void addStartTime(){
        this.dateStart = new Date();
    }
    
    public void saveLog(){
        try
        {
            FileWriter fw = new FileWriter(LOGFILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            //escrever para o ficheiro  out.println(STRING);
            out.flush();
            out.close();
        } 
        catch (IOException e) 
        {
            
        }
    }
}
