package common;

import common.enums.Color;
import common.enums.PieceType;
import common.validators.Validator;

public class Piece {
    private final int id;
    private final Color color;
    private final PieceType type;
    private final Validator validator;

    public Piece (int id, Color color, PieceType type, Validator validator) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.validator = validator;
    }


    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Validator getValidator() {
        return validator;
    }
}
