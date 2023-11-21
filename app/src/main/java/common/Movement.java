package common;

public class Movement {

    private final Coordinate origin;

    private final Coordinate destination;

    public Movement(Coordinate origin, Coordinate destination){
        this.origin = origin;
        this.destination = destination;
    }

    public Coordinate getOrigin() {
        return origin;
    }

    public Coordinate getDestination() {
        return destination;
    }
}
