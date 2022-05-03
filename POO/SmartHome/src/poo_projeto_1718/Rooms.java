package poo_projeto_1718;

public enum Rooms{
    DINING_ROOM, HALL, OFFICE, LIVING_ROOM, GUEST_ROOM, UTILITY_ROOM, TOILET, BEDROOM, OTHER;
    
    @Override
    public String toString(){
        switch(this) {
            case  LIVING_ROOM:
                return "Living Room";
            case DINING_ROOM:
                return "Dining Room";
            case OFFICE:
                return "Office";
            case HALL:
                return "Hall";
            case GUEST_ROOM:
                return "Guest Room";
            case UTILITY_ROOM:
                return "Utility Room";
            case TOILET:
                return "Toilet";
            case BEDROOM:
                return "Bedroom";
            case OTHER:
                return "Other";
            default:
                return "";
        }
    }
}