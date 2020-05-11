import java.math.BigInteger;
import java.util.Objects;

public class Key {
    private BigInteger index;
    private BigInteger cos;
    private BigInteger sin;

    public Key(BigInteger i, BigInteger c, BigInteger s) {
        this.index = i;
        this.cos = c;
        this.sin = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Key key = (Key) o;
        return Objects.equals(index, key.index) &&
                Objects.equals(cos, key.cos) &&
                Objects.equals(sin, key.sin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, cos, sin);
    }
}

