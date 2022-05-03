/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_projeto_1718;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
public class CriarEquipamentosFX extends GridPane{
    
    public CriarEquipamentosFX(final List<Console> items, Room room, Console console, final List<Room> rooms, HashMap<Room, List<Equipment>> equipments, List<Console> savedConsoles){
        
        final ObservableList<Equipment> listaEquipments = FXCollections.observableArrayList(equipments.get(room));//lista apenas dos equipamentos da divisao pretendida
        final ListView<Equipment> lista = new ListView<>(listaEquipments);//list de equipamentos a mostar
        
        StackPane root = new StackPane();
        
        HBox both = new HBox(30);

        VBox left = new VBox(10);
        
        Button btnn = new Button();
        btnn.setText("Auto");
        btnn.setOnAction((ActionEvent event) -> {
            TextInputDialog dialog1 = new TextInputDialog("Temperature");
            dialog1.setTitle("Auto Temperature");
            dialog1.setContentText("Please enter temperature:");
            TextInputDialog dialog2 = new TextInputDialog("Light");
            dialog2.setTitle("Auto Lights");
            dialog2.setContentText("Please enter max light:");
            TextInputDialog dialog3 = new TextInputDialog("Light");
            dialog3.setTitle("Auto Lights");
            dialog3.setContentText("Please enter min light:");
            int temp, maxLight, minLight;
            temp = Integer.parseInt(dialog1.showAndWait().get().trim());
            maxLight = Integer.parseInt(dialog2.showAndWait().get().trim());
            minLight = Integer.parseInt(dialog3.showAndWait().get().trim());
            console.getTemperatureModule().setTemperature(temp);
            console.getLightModule().setMaxIntensitye(maxLight);
            console.getLightModule().setMinIntensitye(minLight);
            if (!console.getAlarmModule().isActivated()) {
                console.getAlarmModule().intruderDetection(room);
                console.getTemperatureModule().autoAirConditioners(room);
                console.getLightModule().autoLamps(room);
            } else {
                console.getTemperatureModule().autoAirConditioners(room);
                console.getAlarmModule().intruderDetection(room);
                console.getLightModule().autoLamps(room);
            }
        } //automatiza a divisao em questao, utiliza os 3 modulos
        );
        
        Button btnnn = new Button();
        btnnn.setText("Alarm Off");
        btnnn.setOnAction((ActionEvent event) -> {
            if(console.getAlarmModule().isActivated()){
                TextInputDialog dialog = new TextInputDialog("Pin");
                dialog.setTitle("Deactivating");
                dialog.setContentText("Insert Pin:");
                Optional<String> result = dialog.showAndWait();
                if (checkPin(result.get())) {
                    console.getAlarmModule().deactivateAlarm(room, result.get());
                } else {
                    error("Pin Input Error", "Pin Has To Be 4 Digits!");
                }
            }else{
                error("Error", "Alarm Not Activated!");
            }
        } //desliga o alarme da divisao
        );
        
        left.getChildren().addAll(criarTexto("Temperature Sensor:", "SanSerif", 15), criarTexto("Light Sensor:", "SanSerif", 15), criarTexto("Motion Sensor:", "SanSerif", 15), criarTexto("Door Sensor:", "SanSerif", 15), criarTexto("Lamp:", "SanSerif", 15), criarTexto("Plug:", "SanSerif", 15), criarTexto("Air Conditioner:", "SanSerif", 15), criarTexto("Camera:", "SanSerif", 15), btnn, btnnn);
        left.setSpacing(15);
        root.getChildren().add(left);

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
        btn.setOnAction((ActionEvent event) -> {//cria equipamentos, adiciona os a divisao, a lista e ao hashmap (a divisao pretendida)
            if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty() && !tf4.getText().isEmpty() && !tf5.getText().isEmpty() && !tf6.getText().isEmpty() && !tf7.getText().isEmpty() && !tf8.getText().isEmpty()) {
                System.out.println(room.getRoomType());
                int aux = Integer.parseInt(tf1.getText());
                for (int i = 0; i < aux; i++) {
                    TemperatureSensor ts = new TemperatureSensor();//cria equipamento
                    room.addTemperatureSensor(ts);//adiciona a divisao
                    listaEquipments.add(ts);//adiciona a listview
                    equipments.get(room).add(ts);//adiciona ao hashmap
                }
                if (aux > 0) {
                    System.out.println(aux + " Temperature Sensor Created!\n");
                }
                aux = Integer.parseInt(tf2.getText());
                for (int i = 0; i < aux; i++) {
                    LightSensor ls = new LightSensor();
                    room.addLightSensor(ls);
                    listaEquipments.add(ls);
                    equipments.get(room).add(ls);
                }
                if (aux > 0) {
                    System.out.println(aux + " Light Sensor Created!\n");
                }
                aux = Integer.parseInt(tf3.getText());
                for (int i = 0; i < aux; i++) {
                    MotionSensor ms = new MotionSensor();
                    room.addMotionSensor(ms);
                    listaEquipments.add(ms);
                    equipments.get(room).add(ms);
                }
                if (aux > 0) {
                    System.out.println(aux + " Motion Sensor Created!\n");
                }
                aux = Integer.parseInt(tf4.getText());
                for (int i = 0; i < aux; i++) {
                    DoorSensor ds = new DoorSensor();
                    room.addDoorSensor(ds);
                    listaEquipments.add(ds);
                    equipments.get(room).add(ds);
                }
                if (aux > 0) {
                    System.out.println(aux + " Door Sensor Created!\n");
                }
                aux = Integer.parseInt(tf5.getText());
                for (int i = 0; i < aux; i++) {
                    Lamp l = new Lamp();
                    room.addLamp(l);
                    listaEquipments.add(l);
                    equipments.get(room).add(l);
                }
                if (aux > 0) {
                    System.out.println(aux + " Lamp Created!\n");
                }
                aux = Integer.parseInt(tf6.getText());
                for (int i = 0; i < aux; i++) {
                    Plug p = new Plug();
                    room.addPlug(p);
                    listaEquipments.add(p);
                    equipments.get(room).add(p);
                }
                if (aux > 0) {
                    System.out.println(aux + " Plug Created!\n");
                }
                aux = Integer.parseInt(tf7.getText());
                for (int i = 0; i < aux; i++) {
                    AirConditioner ac = new AirConditioner();
                    room.addAirConditioner(ac);
                    listaEquipments.add(ac);
                    equipments.get(room).add(ac);
                }
                if (aux > 0) {
                    System.out.println(aux + " Air Conditioner Created!\n");
                }
                aux = Integer.parseInt(tf8.getText());
                for (int i = 0; i < aux; i++) {
                    Camera c = new Camera();
                    room.addCamera(c);
                    listaEquipments.add(c);
                    equipments.get(room).add(c);
                }
                if (aux > 0) {
                    System.out.println(aux + " Camera Created!\n");
                }
            } else {
                error("Input Error", "Invalid Arguments To Create Console!");
            }
        });
        
        Button cancelBtn = new Button("Cancelar");
        cancelBtn.setOnAction((ActionEvent event) -> {//volta atras
            tf1.getScene().setRoot(new CriarDivisoesFX(items, console, rooms, equipments, savedConsoles));
        });
        
        buttons.getChildren().addAll(btn, cancelBtn);
        buttons.setSpacing(10);
        right.getChildren().addAll(tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8, buttons);
        right.setSpacing(10);
        HBox.setHgrow(right, Priority.ALWAYS);
        
        both.getChildren().addAll(left, right, lista);
        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);
        getChildren().addAll(both);
        
        lista.setOnMouseClicked((MouseEvent click) -> {
            if(!listaEquipments.isEmpty()){
                if (click.getClickCount() == 2) {
                    Equipment selectedEquipment = lista.getSelectionModel().getSelectedItem();
                    lista.getScene().setRoot(new VisualizarEquipamentoFX(selectedEquipment, items, room, console, rooms, equipments, savedConsoles));
                }
            }else{                
                error("ListView Empty", "No Rooms To Use!");
            }
        } //ao carregar duas vezes num equipamento, mostra a sua informação
        );
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
     * Cria um erro
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
    /**
     * Verifica se o pin introduzido sao 4 digitos
     * @param pin
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
}
