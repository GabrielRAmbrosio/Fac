package projeto.ipoo.pkg1718;

import java.time.LocalDateTime;
import java.util.Random;

public class Weather{

    private double temperature, humidity, windVelocity, maxTemperature, minTemperature;
    private LocalDateTime date;
    private final Random r;

    public Weather(double temperature, double humidity, double wind){
        r = new Random();
        if(checkTemperature(temperature)){
            this.temperature = temperature;
        }else{
            this.temperature = 20;
        }if(checkHumidity(humidity)){
            this.humidity = humidity;
        }else{
            this.humidity = 45;
        }if(checkWind(wind)){
            windVelocity = wind;
        }else{
            windVelocity = 5;
        }
        maxTemperature = r.nextInt(45);
        minTemperature = r.nextInt((int)maxTemperature);
    }

    private boolean checkTemperature(double x) {
        return !(x < 0 || x > 45);
    }

    private boolean checkHumidity(double x) {
        return !(x < 0 || x > 100);
    }

    private boolean checkWind(double x) {
        return !(x < 0 || x > 100);
    }

    /**
     * Changes the temperature depending on the currentHours (ex. min= 14, max=28, variar 14 graus em 8h/6 as 14 tem de aumentar 14 graus, grausAAumentar/numHoras = graus a aumentar por horas, 1.75 por hora)
     */
    public void changeTemperature(){
        date = LocalDateTime.now();
        if (date.getHour() < 14 || date.getHour() > 6) {
            if ((((maxTemperature - minTemperature) / 8) * date.getHour()) + (maxTemperature - ((14 * maxTemperature - 14 * minTemperature) / 8)) <= 45) {
                temperature = (((maxTemperature - minTemperature) / 8) * date.getHour()) + (maxTemperature - ((14 * maxTemperature - 14 * minTemperature) / 8));
            } else {
                temperature = 45;
            }
        } else {
            if ((((minTemperature - maxTemperature) / 16) * date.getHour()) + (minTemperature - ((30 * minTemperature - 30 * maxTemperature) / 16)) >= 45) {
                temperature = (((minTemperature - maxTemperature) / 16) * date.getHour()) + (minTemperature - ((30 * minTemperature - 30 * maxTemperature) / 16));
            } else {
                temperature = 0;
            }
        }
    }
    /**
     * Changes the wind velocity depending on the previous wind velocity + or - 30 km/h
     */
    public void changeWind() {
        Random random = new Random();
        if((windVelocity - 30) >= 0){
            windVelocity = windVelocity - 30 + (windVelocity + 30 - (windVelocity - 30)) * random.nextDouble();
        }else{
            windVelocity = - windVelocity + 30 + (windVelocity + 30 - (-windVelocity + 30)) * random.nextDouble();
        }
    }
    /**
     * Increases the humidity by 10% between 20h and 8h
     */
    public void changeHumidity() {
        date = LocalDateTime.now();
        if (date.getHour() > 20 || date.getHour()< 8) {
            humidity += 0.10 * humidity;
        }
    }
    
    public void showInfo(){
        System.out.println("Weather\nTemperature: " + temperature + "\nHumidity: " + humidity + "Wind: " + windVelocity + "\n");
    }
    //SELETORES
    public double getTemp() {
        return temperature;
    }
    public double getHum() {
        return humidity;
    }
    public double getWind() {
        return windVelocity;
    }
}