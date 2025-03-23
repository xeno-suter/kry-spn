public class RCTR {
    private static final int MAX_BOUND = 65536; // 2^16
    private final SpnService spnService;

    public RCTR(SpnService spnService) {
        this.spnService = spnService;
    }

    public byte[][] decrypt(byte[][] message) {
        // get initial counter value
        int y = Utils.bytesToInt(message[0]);
        byte[][] decrypted = new byte[message.length][message[0].length];

        for (int i = 0; i < message.length - 1; i++) {
            // prevent counter from growing bigger than 2 ^ (m * n) - 1
            int yi = (y + i) % MAX_BOUND;

            byte[] counterBytes = Utils.intToBytes(yi, message[0].length);
            // encrypt counter to prepare for XOR with yi afterward (can be done in advance to just do XOR when data arrives)
            byte[] encryptedCounter = spnService.encrypt(counterBytes);

            // xor counter with yi to get decrypted value
            decrypted[i] = Utils.xorByteArrays(encryptedCounter, message[i + 1]);
        }

        return decrypted;
    }
}
