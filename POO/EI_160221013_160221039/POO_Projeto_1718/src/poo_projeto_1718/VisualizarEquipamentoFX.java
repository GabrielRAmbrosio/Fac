package poo_projeto_1718;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Gabriel
 */
public class VisualizarEquipamentoFX extends GridPane{
    
    public VisualizarEquipamentoFX(Equipment eq, final List<Console> items, Room room, Console console, final List<Room> rooms, HashMap<Room, List<Equipment>> equipments, List<Console> savedConsoles){
        StackPane root = new StackPane();

        VBox hbox = new VBox(10);
        
        
        HBox top = new HBox(10);
        HBox bot = new HBox(10);
        
        
        hbox.getChildren().addAll(top, bot);
        /**
        * Esta sequencia de else if vai criar uma janela diferente para cada equipamento, porque cada precisa de mostrar diferentes coisas
        */
        if(eq instanceof TemperatureSensor){
            bot.getChildren().add(criarTexto(eq.toString(), "SanSerif", 15));
            if(((TemperatureSensor) eq).isOn()){//se estiver ligado
                Text tx1 = criarTexto("Set On/Off", "SanSerif", 15);
                Text tx2 = criarTexto("Set Current Temperature", "SanSerif", 15);
                
                Slider slider = new Slider(); 
                slider.setMin(-20);
                slider.setMax(70);
                slider.setShowTickLabels(true); 
                slider.setShowTickMarks(true); 
                slider.setMajorTickUnit(10);
                slider.setBlockIncrement(10);
                slider.setValue(((TemperatureSensor) eq).getRoomTemperature());
                
                slider.valueProperty().addListener(new ChangeListener() {//atualiza valor da temp quando mudada
                    @Override
                    public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                        ((TemperatureSensor) eq).setTemperature(slider.getValue());
                    }
                });
                
                RadioButton on = new RadioButton("ON");
                RadioButton off = new RadioButton("OFF");
                final ToggleGroup group = new ToggleGroup();
                on.setToggleGroup(group);
                off.setToggleGroup(group);
                on.setSelected(true);
                on.setOnAction(e -> ((TemperatureSensor) eq).setOnOff(true));//liga
                off.setOnAction(e -> ((TemperatureSensor) eq).setOnOff(false));//desliga
                
                hbox.getChildren().addAll(tx2, slider, tx1, on, off);
            }else{//se nao estiver ligado
                VBox hb = new VBox(10);
                
                RadioButton on = new RadioButton("ON");
                RadioButton off = new RadioButton("OFF");
                final ToggleGroup group = new ToggleGroup();
                on.setToggleGroup(group);
                off.setToggleGroup(group);
                off.setSelected(true);
                on.setOnAction(e -> ((TemperatureSensor) eq).setOnOff(true));//liga
                off.setOnAction(e -> ((TemperatureSensor) eq).setOnOff(false));//desliga
                hb.getChildren().addAll(on,off);
                hbox.getChildren().add(hb);
            }
        }else if(eq instanceof LightSensor){
            bot.getChildren().add(criarTexto(eq.toString(), "SanSerif", 15));
            
            Text tx2 = criarTexto("Set Current Light", "SanSerif", 15);

            Slider slider = new Slider();
            slider.setMin(0);
            slider.setMax(100);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(10);
            slider.setBlockIncrement(10);
            slider.setValue(((LightSensor) eq).getRoomLight());

            slider.valueProperty().addListener(new ChangeListener() {//atualiza valor da luz
                @Override
                public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                    ((LightSensor) eq).setLight(slider.getValue());
                }
            });

            hbox.getChildren().addAll(tx2, slider);
        }else if(eq instanceof MotionSensor){
            bot.getChildren().add(criarTexto(eq.toString(), "SanSerif", 15));
            if(((MotionSensor) eq).isOn()){//se estiver ligado
                Text tx1 = criarTexto("Set On/Off", "SanSerif", 15);
                Text tx2 = criarTexto("Set Motion", "SanSerif", 15);
                
                RadioButton on1 = new RadioButton("ON");
                RadioButton off1 = new RadioButton("OFF");
                final ToggleGroup group1 = new ToggleGroup();
                on1.setToggleGroup(group1);
                off1.setToggleGroup(group1);
                
                
                if(((MotionSensor) eq).hasMotion()){
                    on1.setSelected(true);
                    on1.setOnAction(e -> ((MotionSensor) eq).setMotion(true));//adiciona movimento
                    off1.setOnAction(e -> ((MotionSensor) eq).setMotion(false));
                }else{
                    off1.setSelected(true);
                    on1.setOnAction(e -> ((MotionSensor) eq).setMotion(true));//retira movimento
                    off1.setOnAction(e -> ((MotionSensor) eq).setMotion(false));
                }
                
                RadioButton on = new RadioButton("ON");
                RadioButton off = new RadioButton("OFF");
                final ToggleGroup group = new ToggleGroup();
                on.setToggleGroup(group);
                off.setToggleGroup(group);
                on.setSelected(true);
                on.setOnAction(e -> ((MotionSensor) eq).setOnOff(true));//liga
                off.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent event) { //desliga e retira o movimento
                        ((MotionSensor) eq).setOnOff(false);
                        ((MotionSensor) eq).setMotion(false);
                    }
                });
                hbox.getChildren().addAll(tx2, on1, off1, tx1, on, off);
            }else{//se nao estiver ligado
                VBox hb = new VBox(10);
                
                RadioButton on = new RadioButton("ON");
                RadioButton off = new RadioButton("OFF");
                final ToggleGroup group = new ToggleGroup();
                on.setToggleGroup(group);
                off.setToggleGroup(group);
                off.setSelected(true);
                on.setOnAction(e -> ((MotionSensor) eq).setOnOff(true));//liga
                off.setOnAction(e -> ((MotionSensor) eq).setOnOff(false));//desliga
                hb.getChildren().addAll(on,off);
                hbox.getChildren().add(hb);
            }
        }else if(eq instanceof DoorSensor){
                Text tx1 = criarTexto("Set Open/Closed", "SanSerif", 15);
                
                RadioButton on = new RadioButton("ON");
                RadioButton off = new RadioButton("OFF");
                final ToggleGroup group1 = new ToggleGroup();
                on.setToggleGroup(group1);
                off.setToggleGroup(group1);
                
                on.setOnAction(e -> ((DoorSensor) eq).setOpenClose(true));//liga
                off.setOnAction(e -> ((DoorSensor) eq).setOpenClose(false));//desliga
                
                hbox.getChildren().addAll(tx1, on, off);
        }else if(eq instanceof Lamp){
            bot.getChildren().add(criarTexto(eq.toString(), "SanSerif", 15));
        }else if(eq instanceof Plug){
            top.getChildren().add(criarTexto(eq.getFullName(), "SanSerif", 15));
            final ObservableList<Equipment> listaEquipments = FXCollections.observableArrayList(((Plug) eq).getEquipment());
            final ListView<Equipment> lista = new ListView<>(listaEquipments);//mostra eqwuipamentos ligados a tomada
            bot.getChildren().add(lista);
        }else if(eq instanceof AirConditioner){
            bot.getChildren().add(criarTexto(eq.toString(), "SanSerif", 15));
        }else if(eq instanceof Camera){
            List<String> equipments2 = new ArrayList<>();
            for (Map.Entry<String, String> entry : ((Camera) eq).getContent().entrySet()) {//coloca a key (nome) + value (formato) num arraylist para ser mostrado
                equipments2.add(entry.getKey() + entry.getValue());
            }
            final ObservableList<String> listaEquipments = FXCollections.observableArrayList(equipments2);
            final ListView<String> lista = new ListView<>(listaEquipments);//mostra fotos e videos
            bot.getChildren().add(lista);
        }
        
        Button cancelBtn = new Button("Cancelar");
        cancelBtn.setOnAction((ActionEvent event) -> {//volta atras
            hbox.getScene().setRoot(new CriarEquipamentosFX(items, room, console, rooms, equipments, savedConsoles));
        });
        hbox.getChildren().add(cancelBtn);
        
        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);
        getChildren().addAll(hbox);
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
}


