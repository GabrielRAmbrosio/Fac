package poo_projeto_1718;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */

public class main extends Application{

    public static List<Console> consoles;//consola a ser usada
    public static List<Console> savedConsoles;//consolas guardadas anteriormente
    public static List<Room> rooms;//divisoes criadas
    public static HashMap<Room, List<Equipment>> eqps;//equipamentos criados numa divisao

    public static void main(String[] args) {
        /*
        System.out.println("\t\t#####Criar Consola#####\n");
        Console console = new Console("Gabriel", "Setubal", "PASSWORD_", "1234");//name, address, id, wifi password, alarm pin
        n++;
        System.out.println("Client Name: " + console.getClientName());
        System.out.println("Client Address: " +console.getClientAddress());
        System.out.println("Client ID: " +console.getClientId());
        System.out.println("\n\t\t#####Consola Criada#####\n");
        
        
        System.out.println("\n\t\t#####Criar Divisões#####\n");
        Room bedroom =  new Room(Rooms.BEDROOM);
        console.addRoom(bedroom);
        Room dinningroom =  new Room(Rooms.DINING_ROOM);
        console.addRoom(dinningroom);
        Room guestroom = new Room(Rooms.GUEST_ROOM);
        console.addRoom(guestroom);
        Room hall = new Room(Rooms.HALL);
        console.addRoom(hall);
        Room livingroom =  new Room(Rooms.LIVING_ROOM);
        console.addRoom(livingroom);
        Room toilet = new Room(Rooms.TOILET);
        console.addRoom(toilet);
        console.showRooms();//show rooms added to the console
        System.out.println("\t\t#####Divisões Criadas#####\n");
        
        
        System.out.println("\n\t#####Criar Listas De Equipamentos#####\n");
        console.getRooms().forEach((r) -> {
            System.out.println(r.getSiren().toString());
        });
        
        TemperatureSensor ts3 = new TemperatureSensor();
        System.out.println(ts3.toString());

        LightSensor ls2 = new LightSensor();
        System.out.println(ls2.toString());
        
        DoorSensor ds1 = new DoorSensor();
        System.out.println(ds1.toString());
        
        MotionSensor ms1 = new MotionSensor();
        System.out.println(ms1.toString());
        
        Lamp l2 = new Lamp();
        System.out.println(l2.toString());
        
        Plug p2 = new Plug();
        Plug p3 = new Plug();
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        
        AirConditioner ac3 = new AirConditioner();
        System.out.println(ac3.toString());
        
        
        Camera c1 = new Camera();
        System.out.println(c1.toString());
        
        System.out.println("\t#####Listas De Equipamentos Criadas#####\n");
        
        
        System.out.println("\t\t#####Testar Modulos#####\n");
        System.out.println("##Alarm Module##\n");
        bedroom.addMotionSensor(ms1);//adiciona um sensor de movimento (ms1) ao quarto principal
        bedroom.addDoorSensor(ds1);//adiciona um sensor de porta (ds1) ao quarto principal
        bedroom.addCamera(c1);//adiciona uma camera (c1) ao quarto principal
        
        System.out.println("#Photo Mode#\n");
        console.getAlarmModule().activateAlarms(bedroom, "1234", 1);//ativa o alarme do quarto principal atraves da consola, pin = 1255, modo de alarme = 1 (Foto)
        ms1.setMotion(true);//simula movimento quando o alarme está ativo
        ds1.setOpenClose(true);//simula porta aberta quando o alarme está ativo
        c1.changeCurrentFormat(".jpg");
        console.getAlarmModule().intruderDetection(bedroom);//ve se existe movimento
        c1.showContent();//mostra fotos ou videos tirados
        console.getAlarmModule().deactivateAlarm(bedroom, "1234");//desativa alarme do quarto principal, pin = 1255
        
        System.out.println("#Video Mode#\n");
        console.getAlarmModule().activateAlarms(bedroom, "1235", 2);//ativa o alarme do quarto principal atraves da consola, pin = 1254, modo de alarme = 2(Video)
        ms1.setMotion(true);//simula movimento quando o alarme está ativo
        ds1.setOpenClose(true);//simula porta aberta quando o alarme está ativo
        console.getAlarmModule().intruderDetection(bedroom);//ve se existe movimento
        c1.showContent();//mostra fotos ou videos tirados
        console.getAlarmModule().deactivateAlarm(bedroom, "1235");//desativa alarme do quarto principal, pin = 1255
        
        System.out.println("#Siren Mode#\n");
        console.getAlarmModule().activateAlarms(bedroom, "1236", 3);//ativa o alarme do quarto principal atraves da consola, pin = 1254, modo de alarme = 2(Video)
        ms1.setMotion(true);//simula movimento quando o alarme está ativo
        ds1.setOpenClose(true);//simula porta aberta quando o alarme está ativo
        c1.changeCurrentFormat(".jpg");
        console.getAlarmModule().intruderDetection(bedroom);//ve se existe movimento
        c1.showContent();//mostra fotos ou videos tirados
        console.getAlarmModule().deactivateAlarm(bedroom, "1236");//desativa alarme do quarto principal, pin = 1255
        
        
        
        System.out.println("##Light Module##\n");
        dinningroom.addLightSensor(ls2);//adiciona um sensor de luz a sala de estar
        dinningroom.addLamp(l2);//adiciona uma lampada a sala de estar
        ls2.setLight(0);//mudar luz na sala para ver a resposta do auto
        System.out.println(ls2.toString());
        console.getLightModule().setMinIntensitye(50);//minimo de intensidade de luz pretendida
        console.getLightModule().setMaxIntensitye(75);//maximo de intensidade de luz pretendida
        console.getLightModule().autoLamps(dinningroom);//automatizar lampadas de acordo com o max e min
        System.out.println(ls2.toString());
        console.getLightModule().setSingleLampTo(dinningroom, l2, 0);//mudar manualmente uma lampada
        System.out.println(ls2.toString());
        
        
        
        System.out.println("##Temperature Module##\n");
        guestroom.addTemperatureSensor(ts3);//adiciona um sensor de temperatura ao quarto de hospedes
        guestroom.addAirConditioner(ac3);//adiciona um ar condicionado ao quarto de hospedes
        ts3.setOnOff(true);//ativa o sensor
        ts3.setTemperature(10);
        System.out.println(ts3.toString());
        System.out.println(ac3.toString());
        console.getTemperatureModule().autoAirConditioners(guestroom);//automatizar os sensores e acs
        System.out.println(ts3.toString());
        System.out.println("\t\t#####Modulos Testados#####\n");
        
        System.out.println("\t\t#####Testar Diviões#####\n");
        System.out.println(bedroom.toString());
        System.out.println(dinningroom.toString());
        System.out.println(guestroom.toString());
        System.out.println("\t\t#####Diviões Testadas#####\n");
        
        System.out.println("\t\t#####Testar Wifi#####\n");
        
        System.out.println("##Equipamento <-> Equipamento##\n");
        p2.setPassword("abcd1234");//atualizar password do tomada 2
        System.out.println(p2.getPassword());
        ms1.pairDevice(p2, "abcd1234");//ligar um sensor de movimento a tomada 2
        System.out.println(p2.isPaired());
        System.out.println(p2.getPairingId());
        System.out.println(ms1.getId());
        
        p2.unpairDevice(ms1);//desligar
        System.out.println(p2.isPaired());
        System.out.println(ms1.isPaired());
        
        System.out.println("\n##Consola <-> Equipamento##\n");
        console.pairDevice(p3, "PASSWORD_");//ligar tomada 3 a consola
        System.out.println(p3.isPaired());
        ms1.pairDevice(p3, "0000");//tentar ligar sensor de movimento a tomada 3 (ja esta ligada a consola)
        console.unpairDevice(p2);//desligar tomada 2 (nao ligada)
        console.unpairDevice(p3);//desligar tomada 3
        System.out.println(p3.isPaired());
        System.out.println("\t\t#####Wifi Testada#####\n");
        
        System.out.println("\t\t#####Testar Save Load#####\n");
        save(console, "console.txt");
        Console c = load(console, "console.txt");
        System.out.println(c.toString());
        System.out.println(c.getAlarmModule().toString());
        System.out.println(c.getTemperatureModule().toString());
        System.out.println(c.getLightModule().toString());
        System.out.println("\t\t#####Save Load Testado#####\n");
        */
        launch(args);
    }
    
    /**
     * serialização dos objetos 
     * @param filename
     * @return
     */
    public static Console load( String filename) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Console console = (Console) ois.readObject();
            System.out.println("Read Successful!\n");
            ois.close();
            return console;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * serialização dos objetos 
     * @param console
     * @param filename
     */
    public static void save(Console console, String filename) {
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
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        consoles = new ArrayList<>();
        savedConsoles = new ArrayList<>();
        rooms = new ArrayList<>();
        eqps = new HashMap<>();
        
        savedConsoles.add(createDefault());
        List<String> results = new ArrayList<>();
        File[] files = new File(System.getProperty("user.dir")).listFiles(new FilenameFilter() {//percorre todos os ficheiros no diretorio do projeto e coloca no array os que tiverem a extenção .dat
            @Override public boolean accept(File dir, String name){ 
                return name.endsWith(".dat"); 
            }
        });
        for (File file : files) {//percorre a lista criada com os ficheiros e adiciona os nomes a outra lista
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        for(String file : results){//percorre os ficheiros .dat e faz load do mesmo, guarda as consolas guardadas
            Console c = load(file);
            savedConsoles.add(c);
        }
        
        Scene scene = new Scene(new VisualizadorConsolaFX(consoles, rooms, eqps, savedConsoles), 600, 400);
        primaryStage.setTitle("Gestão de Consolas");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){//ao fechar aplicação, perguntar para guardar
            @Override
            public void handle(WindowEvent we) {
                if (!consoles.isEmpty()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Exit");
                    alert.setHeaderText("Save Console?");
                    ButtonType buttonTypeOne = new ButtonType("Yes");
                    ButtonType buttonTypeTwo = new ButtonType("No");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    
                    if (result.get() == buttonTypeOne) {//guarda e sai
                        TextInputDialog dialog = new TextInputDialog("File Name");
                        dialog.setTitle("Saving");
                        dialog.setHeaderText("Saving Console To File");
                        dialog.setContentText("Please enter file name:");
                        save(consoles.get(0), dialog.showAndWait().get() + ".dat");
                    } else if (result.get() == buttonTypeTwo) {
                        //sai sem guardar
                    } else {
                        we.consume();//se nao carregar sim ou nao continua a aplicação
                    }
                }
            }
        });
        primaryStage.show();
    }

    /**
     *
     * @return
     */
    public Console createDefault(){//cria uma consola default para o utlizador poder escolher
        Console c = new Console("Default", "Default", "0000", "0000");
        
        Room r1 = new Room(Rooms.BEDROOM);
        Room r2 = new Room(Rooms.DINING_ROOM);
        Room r3 = new Room(Rooms.TOILET);
        
        TemperatureSensor ts = new TemperatureSensor();
        LightSensor ls = new LightSensor();
        MotionSensor ms = new MotionSensor();
        DoorSensor ds = new DoorSensor();
        
        Lamp l = new Lamp();
        AirConditioner ac = new AirConditioner();
        Camera cm = new Camera();
        
        r1.addDoorSensor(ds);
        r1.addCamera(cm);
        r1.addMotionSensor(ms);
        
        r2.addLightSensor(ls);
        r2.addLamp(l);
        
        r3.addTemperatureSensor(ts);
        r3.addAirConditioner(ac);
        
        c.addRoom(r1);
        c.addRoom(r2);
        c.addRoom(r3);
        
        return c;
    }
}
