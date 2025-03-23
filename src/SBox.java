import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SBox {
    private static final Map<Byte, Byte> S_BOX = new HashMap<>();

    static {
        List<Integer> temp = List.of(0xE, 0x4, 0xD, 0x1, 0x2, 0xF, 0xB, 0x8, 0x3, 0xA, 0x6, 0xC, 0x5, 0x9, 0x0, 0x7);

        for (int i = 0; i < temp.size(); i++) {
            S_BOX.put(((Integer) i).byteValue(), temp.get(i).byteValue());
        }
    }

    public static byte[] applySBox(byte[] value) {
        return apply(value, S_BOX);
    }

    // get substitution value for block of n bits
    private static byte[] apply(byte[] value, Map<Byte, Byte> box) {
        byte[] bytes = new byte[value.length];

        for (int i = 0; i < value.length; i++) {
            bytes[i] = box.get(value[i]);
        }

        return bytes;
    }
}
