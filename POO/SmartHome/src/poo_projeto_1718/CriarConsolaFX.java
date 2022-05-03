
package poo_projeto_1718;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CriarConsolaFX extends GridPane{

    public CriarConsolaFX(final List<Console> items, Console console, final List<Room> rooms, final HashMap<Room, List<Equipment>> equipments, List<Console> savedConsoles) {
        
        StackPane root = new StackPane();
        
        HBox both = new HBox(30);

        VBox left = new VBox(10);
        left.getChildren().addAll(criarTexto("Nome:", "SanSerif", 15), criarTexto("Morada:", "SanSerif", 15), criarTexto("Pin de Alarme:", "SanSerif", 15), criarTexto("Wifi Password:", "SanSerif", 15));
        left.setSpacing(30);
        root.getChildren().add(left);

        VBox right = new VBox();
        TextField tf1 = new TextField();
        tf1.setPromptText("Nome Completo");
        TextField tf2 = new TextField();
        tf2.setPromptText("Morada");
        TextField tf3 = new TextField();
        tf3.setPromptText("Pin de Alarme");
        TextField tf4 = new TextField();
        tf4.setPromptText("Wifi Password");

        HBox buttons = new HBox();
        
        Button btn = new Button("Confirmar");
        btn.setOnAction((ActionEvent event) -> {//cria consola nova se nao existe ou edita a informação se ja existe
            if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() && !tf4.getText().isEmpty()) {
                if(checkPin(tf3.getText())){//pin 4 digitos
                    if (console == null) {
                        Console console1 = new Console(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText());
                        items.add(console1);
                        rooms.clear();
                        equipments.clear();
                        System.out.println("Consola Criada!\n");
                    } else {//atualiza
                        console.setName(tf1.getText());
                        console.setAddress(tf2.getText());
                        console.setPassword(tf4.getText());
                    }
                    tf1.getScene().setRoot(new VisualizadorConsolaFX(items, rooms, equipments, savedConsoles));
                }else{
                    error("Pin Input Error", "Pin Has To Be 4 Digits!");
                }
            } else {
                error("Input Error", "Invalid Arguments To Create Console!");
            }
        });
        
        Button cancelBtn = new Button("Cancelar");
        cancelBtn.setOnAction((ActionEvent event) -> {//volta ao ecra anterior
            tf1.getScene().setRoot(new VisualizadorConsolaFX(items, rooms, equipments, savedConsoles));
        });
        buttons.getChildren().addAll(btn, cancelBtn);
        buttons.setSpacing(20);
        right.getChildren().addAll(tf1,tf2,tf3,tf4,buttons);
        right.setSpacing(25);
        HBox.setHgrow(right, Priority.ALWAYS);
        
        both.getChildren().addAll(left, right);
        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);
        getChildren().addAll(both);
    }
    /**
     * Cria um texto
     */
    private Text criarTexto(String frase, String nomeFonte, int tamanho) {
        Text texto = new Text(frase);
        Font fonte = Font.font(nomeFonte, tamanho);
        texto.setFont(fonte);
        return texto;
    }
    /**
     * Verifica se o pin introduzido sao 4 digitos
     * @param pin - pin
     * @return 
     */
    public boolean checkPin(String pin){
        List<Integer> numbers;
        numbers = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        if(pin.length() == 4){
            for(int i = 0; i < 4; i++){
                int num = Character.getNumericValue(pin.charAt(i));
                if(!numbers.contains(num)){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
    /**
     * Cria mensagem de erro
     * @param errorTitle
     * @param errorText
     */
    public void error(String errorTitle, String errorText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(errorTitle);
        alert.setHeaderText(null);
        alert.setContentText(errorText);
        alert.showAndWait();
    }
}
