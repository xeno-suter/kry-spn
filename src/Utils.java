import java.util.stream.IntStream;

public class Utils {
    public static byte[] xorByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length];

        IntStream.range(0, a.length)
                .forEach(i -> result[i] = (byte) (a[i] ^ b[i]));

        return result;
    }

    public static int bytesToInt(byte[] bytes) {
        int value = 0;
        for (byte b : bytes) {
            value = (value << 4) + (b & 0x0F);
        }

        return value;
    }

    public static byte[] intToBytes(int value, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[length - i - 1] = (byte) (value &  0x0F);
            value >>= 4;
        }
        return bytes;
    }
}
