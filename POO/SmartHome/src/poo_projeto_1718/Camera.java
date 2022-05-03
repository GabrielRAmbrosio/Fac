package poo_projeto_1718;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Camera extends Equipment{
    
    private Map<String, String> content;
    private List<String> photoFormats;
    private List<String> videoFormats;
    private String currentFormat;
    
    /**
     * Construtor da camera chama metodo super por extender a classe equipment, inicializa um hasmap que contem as fotos e videos tirados
     * uma lista que contem os formatos das fotos e outro que possui os formatos dos videos
     */
    public Camera(){
        super("Camera");
        content = new HashMap<>();
        photoFormats = new ArrayList<>();
        videoFormats = new ArrayList<>();
        photoFormats = Arrays.asList(".jpeg",".gif", ".bmp", ".png", ".jpg", ".tiff");
        videoFormats = Arrays.asList(".flv", ".avi", ".wmv", ".mpg");
        currentFormat = ".jpeg";
    }
    
    private boolean checkString(String name){
        return !(name == null || name.equals(""));
    }

    public Map<String, String> getContent(){
        return content;
    }

    /**
     * Muda o formato que vai ser utilizafdo parta tirar a foto ou video
     * @param format - formato
     * @return
     */
    public boolean changeCurrentFormat(String format){
        if(checkString(format)){
            if(format.charAt(0) == '.'){
                if(photoFormats.contains(format) || videoFormats.contains(format)){
                    System.out.println("Format Changed! " + format + "\n");
                    currentFormat = format;
                    return true;
                }else{
                    System.out.println("Format Not Supported!\n");
                    return false;
                }
            }else{
                System.out.println("Format Not Supported! (Start format with '.')\n");
                return false;
            }
        }else{
            System.out.println("ERROR: Format Values Invalid!\n");
            return false;
        }
    }
    
    /**
     *Tira uma foto ou video com o formato escolhido anteriormente
     * @param content
     */
    public void take(String content){
        this.content.put(content, currentFormat);
    }
    
    /**
     * apaga uma foto ou video tirado
     * @param content
     * @return
     */
    public boolean removeContent(String content){
        if(this.content.containsKey(content)){
            this.content.remove(content);
            System.out.println("Photo/Video Removed!");
            return true;
        }else{
            System.out.println("ERROR: Photo/Video Does Not Exists!");
            return false;
        }
    }

    public List<String> getPhotoFormats(){
        return photoFormats;
    }

    public List<String> getVideoFormats(){
        return videoFormats;
    }
    
    //Shows

    /**
     * Mostra fotos e videos
     */
    public void showContent(){
        System.out.println("Photos & Videos:");
        content.entrySet().forEach((entry) -> {
            System.out.println(entry.getKey() + entry.getValue() + "\n");
        });
    }

    /**
     * Mostra todos os formatos suportados
     */
    public void showFormats(){
        System.out.println("Formats:");
        photoFormats.forEach((f) -> {
            System.out.print(f + " ");
        });
        System.out.println();
        videoFormats.forEach((f) -> {
            System.out.print(f + " ");
        });
        System.out.println("\n");
    }

    public String getCurrentFormat(){
        return currentFormat;
    }

    @Override
    public String toString(){
        return super.toString() + "\n";
    }
}
