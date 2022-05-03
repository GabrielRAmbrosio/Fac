package Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * Padrao Singleton
 * Classe Logger que cria um ficheiro txt, e atualiza-o sempre que existir uma alteração na aplicação
 * @author Gabriel Ambrósio 160221013
 */
public final class Logger{
    
    private static Logger instance = new Logger();
    
    private static final String LOGGERFILE = "logger.txt";
    private PrintStream printStream;
    
    private Logger(){
        connect();
    }
    
    /**
     *
     * @return
     */
    public static Logger getInstance(){
        return instance;
    }

    /**
     * metodo que inicializa o atributo PrintStream, usando o ficheiro default
     * @return
     */
    public boolean connect() {
        if (printStream == null) {
            try {
                printStream = new PrintStream(new FileOutputStream(LOGGERFILE), true);
            } catch (FileNotFoundException ex) {
                printStream = null;
                return false;

            }
            return true;
        }
        return true;
    }
    
    /**
     * Metodo usado para escrever para o ficheiro
     * @param str - string a escrever no log
     */
    public void saveLog(String str){
        try {
            printStream.println(new Date().toString() + "  " + str);
        } catch (LoggerException e) {

        }
    }
}
