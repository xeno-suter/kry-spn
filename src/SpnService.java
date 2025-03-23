import java.util.Arrays;

public class SpnService {
    private static final byte[] MASTER_KEY = {0x3, 0xA, 0x9, 0x4, 0xD, 0x6, 0x3, 0xF};
    private final int rounds;

    public SpnService(int rounds) {
        this.rounds = rounds;
    }

    public byte[] encrypt(byte[] message) {
        byte[] result = Arrays.copyOf(message, message.length);

        // generate round key for first round and do XOR with round key (initialer Weissschritt)
        byte[] roundKey = generateRoundKey(0);
        result = Utils.xorByteArrays(result, roundKey);

        // do sBox replacement, permutation and xor with generated round key for r - 1 regular rounds
        for (int i = 1; i < rounds; i++) {
            result = SBox.applySBox(result);
            result = PermutationMatrix.permute(result);
            roundKey = generateRoundKey(i);
            result = Utils.xorByteArrays(result, roundKey);
        }

        // last round don't do permutation
        result = SBox.applySBox(result);
        roundKey = generateRoundKey(rounds);
        result = Utils.xorByteArrays(result, roundKey);

        return result;
    }

    private byte[] generateRoundKey(int round) {
        return Arrays.copyOfRange(MASTER_KEY, round, round + 4);
    }
}
