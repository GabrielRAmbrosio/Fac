package poo_projeto_1718;

/**
 * Interface Wifi que ajuda na gestao dos equipamentos ligados nas classes MotionSensor e Plug
*/
public interface Wifi{
        public void pairDevice(WifiEquipment equipment, String password);
        public void unpairDevice(WifiEquipment equipment);
}
