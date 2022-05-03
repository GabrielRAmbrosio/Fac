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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 *
 * @author Gabriel
 */
public class VisualizadorConsolaFX extends VBox{
    
    public VisualizadorConsolaFX(final List<Console> items, final List<Room> rooms, final HashMap<Room, List<Equipment>> equipments, List<Console> savedConsoles){
        
        Label labelLista = new Label("Consolas");
        labelLista.setPadding( new Insets(10));
        
        final ObservableList<Console> listaConsolas = FXCollections.observableArrayList(items);//view list da consola em que estamosa  trabalhar
        final ListView<Console> lista = new ListView<>(listaConsolas);
        
        final ObservableList<Console> listaConsolasGuardadas = FXCollections.observableArrayList(savedConsoles);//viewlist da consola guardada
        final ListView<Console> listaGuardadas = new ListView<>(listaConsolasGuardadas);
        
        HBox hbox = new HBox(30);
        
        Button createBtn = new Button("Criar");
        createBtn.setOnAction((ActionEvent event) -> {//criar nova consola
            if(items.size() <= 0){
                lista.getScene().setRoot(new CriarConsolaFX(items, null, rooms, equipments, savedConsoles));
            }else{
                error("Creation Error", "Only One Console Alowed!");
            }
        });
        
        Button editBtn = new Button("Editar");
        editBtn.setOnAction((ActionEvent event) -> {//editar consola ja existente
            Console console = lista.getSelectionModel().getSelectedItem();
            if (!items.isEmpty()) {
                    int index;
                    index = lista.getSelectionModel().getSelectedIndex();
                    if (index != -1) {
                        lista.getScene().setRoot(new CriarConsolaFX(items, console, rooms, equipments, savedConsoles));
                    }
                    if (index == -1) {
                        error("Selection Error", "Choose Console To Edit!");
                    }
                } else {
                    error("ListView Empty", "No Console To Edit!");
                }
        });
        
        Button deleteBtn = new Button("Apagar");
        deleteBtn.setOnAction((ActionEvent event) -> {//apagar consola existente
            Console console = lista.getSelectionModel().getSelectedItem();
                if (!items.isEmpty()) {
                    int index;
                    index = lista.getSelectionModel().getSelectedIndex();
                    if (index != -1) {
                        listaConsolas.remove(console);
                        items.remove(console);
                        System.out.println("Consola Removida!");
                    }
                    if (index == -1) {
                        error("Selection Error", "Choose Console To Delete!");
                    }
                } else {
                    error("ListView Empty", "No Console To Erase!");
                }
        });
        
        hbox.getChildren().addAll(createBtn, editBtn, deleteBtn);
        setPadding(new Insets(10));
        setSpacing(10);
        getChildren().addAll(labelLista, lista, listaGuardadas, hbox);
        
        lista.setOnMouseClicked((MouseEvent click) -> {
            if(!items.isEmpty()){
                if (click.getClickCount() == 2) {
                    Console selectedConsole = lista.getSelectionModel().getSelectedItem();
                    lista.getScene().setRoot(new CriarDivisoesFX(items, selectedConsole, rooms, equipments, savedConsoles));
                }
            }else{
                error("ListView Empty", "No Console To Use!");
            }
        } //ao carregar duas vezes na consola em que queremos trabalhar, podemos criar as divisoes
        );
        
        listaGuardadas.setOnMouseClicked(new EventHandler<MouseEvent>(){//ao carregar na consola guardada, vamos restaurar o conteudo da mesma
            @Override
            public void handle(MouseEvent click){
                if(!savedConsoles.isEmpty()){
                    if(click.getClickCount() == 2){
                    Console selectedConsole = listaGuardadas.getSelectionModel().getSelectedItem();
                        if(!items.isEmpty()){
                            if (selectedConsole == items.get(0)) {//se a consola guardada ja foi restaurada
                                lista.getScene().setRoot(new CriarDivisoesFX(items, selectedConsole, rooms, equipments, savedConsoles));
                            }else{//senao adiciona a consola guardada a viewlist principal
                                items.clear();
                                items.add(selectedConsole);
                            }
                        }else{//inicializar as viewlists com o conteudo da consola guardada e prossegue
                            items.clear();
                            items.add(selectedConsole);
                            rooms.clear();
                            rooms.addAll(selectedConsole.getRooms());
                            equipments.clear();
                            for(Room r : selectedConsole.getRooms()){
                                List<Equipment> v = new ArrayList<>();
                                equipments.put(r, v);
                                r.getTempSensors().forEach((ts) -> {
                                    equipments.get(r).add(ts);
                                });
                                r.getLightSensors().forEach((ls) -> {
                                    equipments.get(r).add(ls);
                                });
                                r.getMotionSensors().forEach((ms) -> {
                                    equipments.get(r).add(ms);
                                });
                                r.getDoorSensors().forEach((ds) -> {
                                    equipments.get(r).add(ds);
                                });
                                r.getLamps().forEach((l) -> {
                                    equipments.get(r).add(l);
                                });
                                r.getPlugs().forEach((p) -> {
                                    equipments.get(r).add(p);
                                });
                                r.getAcs().forEach((ac) -> {
                                    equipments.get(r).add(ac);
                                });
                                r.getCameras().forEach((c) -> {
                                    equipments.get(r).add(c);
                                });
                            }
                            lista.getScene().setRoot(new CriarDivisoesFX(items, selectedConsole, rooms, equipments, savedConsoles));
                        }
                    }
                }else{
                    error("ListView Empty", "No Console To Use!");
                }
                
            }
        });
        
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
