public class RCTR {
    private static final int MAX_BOUND = 65536; // 2^16
    private final SpnService spnService;

    public RCTR(SpnService spnService) {
        this.spnService = spnService;
    }

    public byte[][] decrypt(byte[][] message) {
        int y = Utils.bytesToInt(message[0]);
        byte[][] decrypted = new byte[message.length][message[0].length];

        for (int i = 0; i < message.length - 1; i++) {
            int yi = (y + i) % MAX_BOUND;

            byte[] counterBytes = Utils.intToBytes(yi, message[0].length);
            byte[] encryptedCounter = spnService.encrypt(counterBytes);

            decrypted[i] = Utils.xorByteArrays(encryptedCounter, message[i + 1]);
        }

        return decrypted;
    }
}
