import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OpenAddressingHashMapTest {
    private OpenAddressingHashMap openAddressingHashMap;
    private OpenAddressingHashMap openAddressingHashMapWithLength;

    @Before
    public void prepareObjects() {
        openAddressingHashMap = new OpenAddressingHashMap();
        openAddressingHashMapWithLength = new OpenAddressingHashMap(100);
    }

    @Test
    public void getKeys_returnCorrectLengthForDefaultConstructor() {
        assertEquals(openAddressingHashMap.getKeys().length, 16);
    }

    @Test
    public void getKeys_returnCorrectLengthForParametrizedConstructor() {
        assertEquals(openAddressingHashMapWithLength.getKeys().length, 100);
    }

    @Test
    public void getValues_returnCorrectLengthForDefaultConstructor() {
        assertEquals(openAddressingHashMap.getValues().length, 16);
    }

    @Test
    public void getValues_returnCorrectLengthForParametrizedConstructor() {
        assertEquals(openAddressingHashMapWithLength.getValues().length, 100);
    }

    @Test
    public void put_oneValueForDefaultLength_rightHash() {
        openAddressingHashMap.put(1, 1L);
        assertEquals(openAddressingHashMap.getKeys()[1 % 16], Integer.valueOf(1));
    }

    @Test
    public void put_oneValueForSetLength_rightHash() {
        openAddressingHashMapWithLength.put(1, 1L);
        assertEquals(openAddressingHashMapWithLength.getKeys()[1 % 100], Integer.valueOf(1));
    }

    @Test
    public void put_oneValueForDefaultLength_rightValue() {
        openAddressingHashMap.put(1, 1L);
        assertEquals(openAddressingHashMap.getValues()[1 % 16], Long.valueOf(1));
    }

    @Test
    public void put_oneValueForSetLength_rightValue() {
        openAddressingHashMapWithLength.put(1, 1L);
        assertEquals(openAddressingHashMapWithLength.getValues()[1 % 100], Long.valueOf(1));
    }

    @Test
    public void put_10kPairsForDefaultLength_getNotNullForAll() {
        for (int i = 0; i < 10_000; i++) {
            openAddressingHashMap.put(i, (long) i);
        }
        for (int i = 0; i < 10_000; i++) {
            assertNotNull(openAddressingHashMap.get(i));
        }
    }

    @Test
    public void put_10kPairsForSetLength_getNotNullForAll() {
        for (int i = 0; i < 10_000; i++) {
            openAddressingHashMapWithLength.put(i, (long) i);
        }
        for (int i = 0; i < 10_000; i++) {
            assertNotNull(openAddressingHashMapWithLength.get(i));
        }
    }

    @Test
    public void put_10kPairsForDefaultLength_getRightValuesByKey() {
        for (int i = 0; i < 10_000; i++) {
            openAddressingHashMap.put(i, (long) i);
        }
        for (int i = 0; i < 10_000; i++) {
            assertEquals(openAddressingHashMap.get(i), Long.valueOf(i));
        }
    }

    @Test
    public void put_10kPairsForSetLength_getRightValuesByKey() {
        for (int i = 0; i < 10_000; i++) {
            openAddressingHashMapWithLength.put(i, (long) i);
        }
        for (int i = 0; i < 10_000; i++) {
            assertEquals(openAddressingHashMapWithLength.get(i), Long.valueOf(i));
        }
    }

    @Test
    public void size_10kPairsForDefaultLength_10kSize() {
        for (int i = 0; i < 10_000; i++) {
            openAddressingHashMap.put(i, (long) i);
        }
        assertEquals(openAddressingHashMap.size(), 10_000);
    }

    @Test
    public void size_10kPairsForSetLength_10kSize() {
        for (int i = 0; i < 10_000; i++) {
            openAddressingHashMapWithLength.put(i, (long) i);
        }
        assertEquals(openAddressingHashMapWithLength.size(), 10_000);
    }
}
