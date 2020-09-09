import java.util.Arrays;

public class OpenAddressingHashMap {

    private Integer [] keys;
    private Long [] values;
    private int length;
    private int size;

    public OpenAddressingHashMap() {
        length = 16;
        keys = new Integer [length];
        values = new Long [length];
    }

    public OpenAddressingHashMap(int length) {
        this.length = length;
        keys = new Integer [length];
        values = new Long [length];
    }

    public void put(Integer key, Long value) {
        int bucket = key.hashCode() % length;

        for (int i = bucket; i < length; i++) {
            if (keys[i] == null) {
                keys[i] = key;
                values[i] = value;

                size++;
                return;
            } else if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        resize();
        put(key, value);
    }

    private void resize() {
        length += length / 2;
        Integer [] biggerKeys = new Integer[length];
        Long [] biggerValues = new Long[length];

        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                int newHash = keys[i].hashCode() % length;
                biggerKeys[newHash] = keys[i];
                biggerValues[newHash] = values[i];
            }
        }
        keys = biggerKeys;
        values = biggerValues;
    }

    public Long get(Integer key) {
        int hash = key.hashCode() % length;

        for (int i = hash; i < length; i++) {
            if (keys[i] == null) {
                throw new RuntimeException("No such element");
            }
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        throw new RuntimeException("No such element");
    }

    public int size() {
        return size;
    }

    public Integer [] getKeys() {
        return keys;
    }

    public Long [] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "OpenAddressingHashMap{" +
                "keys=" + Arrays.toString(keys) +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
