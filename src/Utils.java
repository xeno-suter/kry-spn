import java.util.stream.IntStream;

public class Utils {
    public static byte[] xorByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length];

        IntStream.range(0, a.length)
                .forEach(i -> result[i] = (byte) (a[i] ^ b[i]));

        return result;
    }

    // convert byte array of m length of n bits back to chunk size (m * n)
    public static int bytesToInt(byte[] bytes) {
        int value = 0;
        for (byte b : bytes) {
            value = (value << 4) + (b & 0x0F);
        }

        return value;
    }

    // convert m * n input into byte array of m length containing n bits
    public static byte[] intToBytes(int value, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[length - i - 1] = (byte) (value &  0x0F);
            value >>= 4;
        }
        return bytes;
    }
}
