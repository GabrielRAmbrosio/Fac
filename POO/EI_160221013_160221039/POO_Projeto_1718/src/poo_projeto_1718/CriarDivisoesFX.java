package poo_projeto_1718;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Gabriel
 */
public class CriarDivisoesFX extends GridPane{
    
    public CriarDivisoesFX(final List<Console> items, Console console, final List<Room> rooms, final HashMap<Room, List<Equipment>> equipments, List<Console> savedConsoles){
        
        final ObservableList<Room> listaRooms = FXCollections.observableArrayList(rooms);
        final ListView<Room> lista = new ListView<>(listaRooms);//list view das divioes a mostrar
        
        StackPane root = new StackPane();
        
        HBox both = new HBox(30);

        VBox left = new VBox(10);
        left.getChildren().addAll(criarTexto("Living Room:", "SanSerif", 15), criarTexto("Dining Room:", "SanSerif", 15), criarTexto("Office:", "SanSerif", 15), criarTexto("Hall:", "SanSerif", 15), criarTexto("Guest Room:", "SanSerif", 15), criarTexto("Utility Room:", "SanSerif", 15), criarTexto("Toilet:", "SanSerif", 15), criarTexto("Bedroom:", "SanSerif", 15));
        left.setSpacing(15);

        VBox right = new VBox();
        TextField tf1 = new TextField();
        tf1.setText("0");
        TextField tf2 = new TextField();
        tf2.setText("0");
        TextField tf3 = new TextField();
        tf3.setText("0");
        TextField tf4 = new TextField();
        tf4.setText("0");
        TextField tf5 = new TextField();
        tf5.setText("0");
        TextField tf6 = new TextField();
        tf6.setText("0");
        TextField tf7 = new TextField();
        tf7.setText("0");
        TextField tf8 = new TextField();
        tf8.setText("0");
        
        HBox buttons = new HBox();
        
        Button btn = new Button("Confirmar");
        btn.setOnAction((ActionEvent event) -> {//cria as divisoes que querermos, adiciona as a consola (para poder fazer save()), adiciona as a list view para mostrar e adiciona ao hashmap de contem os equipamentos da divisao criada 
            if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() && !tf4.getText().isEmpty() && !tf5.getText().isEmpty() && !tf6.getText().isEmpty() && !tf7.getText().isEmpty() && !tf8.getText().isEmpty()) {
                int aux = Integer.parseInt(tf1.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.LIVING_ROOM);//cria divisao
                    console.addRoom(room);//adiciona a consola
                    listaRooms.add(room);//adiciona a listview
                    final List<Equipment> listaEqu = new ArrayList<>(); // lista de equipamentos da divisao
                    equipments.put(room, listaEqu);//HashMap<Room, List<Equipment>>
                }
                if (aux > 0) {
                    System.out.println(aux + " Living Room Created!\n");
                }
                aux = Integer.parseInt(tf2.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.DINING_ROOM);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " Dining Room Created!\n");
                }
                aux = Integer.parseInt(tf3.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.OFFICE);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " Office Created!\n");
                }
                aux = Integer.parseInt(tf4.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.HALL);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " Hall Created!\n");
                }
                aux = Integer.parseInt(tf5.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.GUEST_ROOM);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " Guest Room Created!\n");
                }
                aux = Integer.parseInt(tf6.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.UTILITY_ROOM);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " Utility Room Created!\n");
                }
                aux = Integer.parseInt(tf7.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.TOILET);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " Toilet Created!\n");
                }
                aux = Integer.parseInt(tf8.getText());
                for (int i = 0; i < aux; i++) {
                    Room room = new Room(Rooms.BEDROOM);
                    console.addRoom(room);
                    listaRooms.add(room);
                    final List<Equipment> listaEqu = new ArrayList<>();
                    equipments.put(room, listaEqu);
                }
                if (aux > 0) {
                    System.out.println(aux + " BedRoom Created!\n");
                }
            }
        });
        
        Button cancelBtn = new Button("Cancelar");
        cancelBtn.setOnAction((ActionEvent event) -> {//volta atras
            tf1.getScene().setRoot(new VisualizadorConsolaFX(items, listaRooms, equipments, savedConsoles));
        });
        
        right.getChildren().addAll(tf1,tf2,tf3,tf4, tf5, tf6, tf7, tf8, buttons);
        right.setSpacing(10);
        HBox.setHgrow(right, Priority.ALWAYS);
        
        both.getChildren().addAll(left, right, lista);
        
        lista.setOnMouseClicked((MouseEvent click) -> {
            if(!listaRooms.isEmpty()){
                if (click.getClickCount() == 2) {
                    Room selectedRoom = lista.getSelectionModel().getSelectedItem();
                    lista.getScene().setRoot(new CriarEquipamentosFX(items, selectedRoom, console, listaRooms, equipments, savedConsoles));
                }
            }else{                
                error("ListView Empty", "No Rooms To Use!");
            }
        } //ao carregar duas vezes numa divisao, podemos criar os diversos equipamentos para a mesma
        );
        
        buttons.getChildren().addAll(btn, cancelBtn);
        buttons.setSpacing(20);
        
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
     * Cria uma mensagem de erro
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
