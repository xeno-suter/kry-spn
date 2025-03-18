public class PermutationMatrix {
    public static final int[] PERMUTATION_MATRIX = new int[] {0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};

    public static byte[] permute(byte[] bytes) {
        int value = Utils.bytesToInt(bytes);
        int newValue = 0;
        for (int i = 0; i < 16; i++) {
            int bit = (value >> i) & 1;
            newValue |= (bit << PERMUTATION_MATRIX[i]);
        }

        return Utils.intToBytes(newValue, 4);
    }
}