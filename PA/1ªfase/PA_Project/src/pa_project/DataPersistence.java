package pa_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Gabriel
 */
public class DataPersistence {
    public static Percurso load( String filename) throws ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Percurso percurso = (Percurso) ois.readObject();
            System.out.println("Read Successful!\n");
            ois.close();
            return percurso;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * serialização dos objetos 
     * @param console
     * @param filename
     */
    public static void save(Percurso console, String filename) {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(console);
                oos.flush();
                oos.close();
                System.out.println("Save Successful!\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
