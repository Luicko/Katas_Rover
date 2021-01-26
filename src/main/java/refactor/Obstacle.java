package refactor;

import refactor.SimpleViewPoint.Position;

public class Obstacle {
    private final Position position;

    public Obstacle(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}