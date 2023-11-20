package common;

import java.util.Objects;
public record Coordinate(int column, int row) { //coordenadas
    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Coordinate coordinate)) return false;
        return coordinate.column() == this.column() && coordinate.row() == this.row();
    }
    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
