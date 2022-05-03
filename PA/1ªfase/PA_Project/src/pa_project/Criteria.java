package pa_project;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public enum Criteria {
        DISTANCE,
        COST;

        public String getUnit() {
            switch (this) {
                case COST:
                    return "€";
                case DISTANCE:
                    return "Miles";
            }
            return "Unknown";
        }
    };