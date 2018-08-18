import java.util.Random;

public class GenerateNounce {
    public static void main(String[] args) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        long timestamp = System.currentTimeMillis() / 1000;
        Random random = new Random();
        random.setSeed(timestamp);

        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }

        System.out.println(sb.toString());
    }
}
