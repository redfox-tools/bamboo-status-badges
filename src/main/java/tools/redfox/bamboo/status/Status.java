package tools.redfox.bamboo.status;

public enum Status {
    IN_PROGRESS("yellow"),
    FAILED("critical"),
    SUCCESS("success"),
    UNKNOWN("lightgrey");

    private String colour;

    Status(String colour) {
        this.colour = colour;
    }

    public String colour() {
        return colour;
    }

    @Override
    public String toString() {
        switch (this) {
            case SUCCESS:
                return "SUCCESS";
            case FAILED:
                return "FAILED";
            case IN_PROGRESS:
                return "IN PROGRESS";
            default:
                return "UNKNOWN";
        }
    }
}
