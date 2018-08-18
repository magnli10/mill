import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
    public static byte[] decrypt() throws Exception {
        byte[] content = hexStringToByteArray(new String(""));
        byte [] key = new String("KtJqcU4wLY5mFt93").getBytes();

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, skeySpec, new IvParameterSpec(key));
        return cipher.doFinal(content);
    }

    public static void main(String[] args) {

        try{
            System.out.println(decrypt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
