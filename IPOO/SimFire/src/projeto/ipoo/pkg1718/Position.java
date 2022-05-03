package projeto.ipoo.pkg1718;

public class Position {

    private double latitude;
    private double longitude;

    public Position(double newLatitude, double newLongitude) {
        if (checkCoordinates(newLatitude, newLongitude)) {
            latitude = newLatitude;
            longitude = newLongitude;
        }
    }

    /**
     * Retruns the kilometers from the position of the object used to the position with the newLatitude and newLongitude
     * @param newLatitude - new position latitude
     * @param newLongitude - new position longitude
     * @return kilometers - kilometers to new position
    */
    public double getKilometersTo(double newLatitude, double newLongitude) {
        double kilometers;
        double x1 = latitude * 111.12;
        double y1 = longitude * 111.12;
        double x2 = newLatitude * 111.12;
        double y2 = newLongitude * 111.12;
        kilometers = Math.sqrt((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2)));
        return kilometers;
    }
    
    /**
     * Returns the kilometers between the two positions given
     * @param p - first position
     * @param p1 - second position
     * @return kilometers - kilometers to new position
    */
    public double getKilometersFromTo(Position p, Position p1) {
        double kilometers;
        double y = (p1.getLatitude() - p.getLatitude());
        double x = (p1.getLongitude() - p.getLongitude());
        double y2 = Math.pow(y, 2);
        double x2 = Math.pow(x, 2);
        kilometers = Math.sqrt(y2 + x2) * 111.12;
        return kilometers;
    }
    
    public double getTimeFromTo(Position p, Position p1) {
        double kilometers;
        double y = (p1.getLatitude() - p.getLatitude());
        double x = (p1.getLongitude() - p.getLongitude());
        double y2 = Math.pow(y, 2);
        double x2 = Math.pow(x, 2);
        kilometers = Math.sqrt(y2 + x2) * 111.12;
        return kilometers/60;
    }
    
    /**
     * Calculates the time needed to go from one position to another (assuming the object moves at a constant 60kn/h)
     * @param p - current position
     * @param p1 - to go position
    */
    public void calculateTime(Position p, Position p1){
        double totalTime = getKilometersFromTo(p, p1) / 60;
        int hours = (int) totalTime;
        double minutesDecimal = totalTime - hours;
        double minutesDecimal2 = (minutesDecimal * 60);
        int minutes = (int) minutesDecimal2;
        double secondsDecimal = minutesDecimal2 - minutes;
        double seconds = (secondsDecimal * 60);
        int secondsCast = (int) seconds;
        System.out.println(hours + "h" + minutes + "min" + secondsCast + "sec");
    }

    public final boolean checkCoordinates(double newLatitude, double newLongitude) {
        if(newLatitude < -90 || newLatitude > 90){
            //latitude invalida
            System.out.println("ERROR: Invalid Latitude, Value Beteewn -90 e 90.");
            return false;
        }else{
            if(newLongitude < -180 || newLongitude > 180){
                //longitude invalida
                System.out.println("ERROR: Invalid Longitude, Value Beteewn -180 e 180.");
                return false;
            }else{
                return true;
            }
        }
    }

    public boolean isEqual(Position newPosition) {
        return latitude == newPosition.getLatitude() && longitude == newPosition.getLongitude();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLatitudeNorteSul() {
        if(latitude > 0){
            return " North";
        }else{
            return " South";
        }
    }

    public String getLongitudeNorteSul() {
        if(longitude > 0){
            return " East";
        }else{
            return " West";
        }
    }

    public void showInfo(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        String temp = "";
        temp += "Position: " + latitude + getLatitudeNorteSul() + " " + longitude + getLongitudeNorteSul();
        return temp;
    }
}
