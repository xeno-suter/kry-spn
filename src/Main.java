import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String c = "000001001101001000001011101110000000001010001" +
                   "111100011100111111101100000010100010100001110" +
                   "10000000010011011001110010101110110000";

        RCTR rctr = new RCTR(new SpnService(4));

        String[] splittedString = splitStringByLength(c);
        byte[][] encrypted = new byte[splittedString.length][4];

        for (int i = 0; i < splittedString.length; i++) {
            // convert m * n bit input (int value) into a byte array of m length each block containing n bits
            encrypted[i] = Utils.intToBytes(Integer.parseInt(splittedString[i], 2), 4);
        }

        StringBuilder builder = new StringBuilder();
        byte[][] decrypted = rctr.decrypt(encrypted);
        // decode bits to text
        for (byte[] bytes : decrypted) {
            builder
                    .append((char) ((bytes[0] << 4) + bytes[1]))
                    .append((char) ((bytes[2] << 4) + bytes[3]));
        }

        System.out.println(builder);
    }

    // split encrypted input into m * n chunks
    private static String[] splitStringByLength(String str) {
        int chunkSize = 16;
        List<String> chunks = new ArrayList<>();

        for (int i = 0; i < str.length(); i += chunkSize) {
            int end = Math.min(i + chunkSize, str.length());
            chunks.add(str.substring(i, end));
        }

        return chunks.toArray(new String[0]);
    }
}