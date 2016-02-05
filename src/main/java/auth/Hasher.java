package auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    public static String hash(String password) {
        try {


            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(password.getBytes());

            byte[] messageDigest = instance.digest();
            String s = convertByteArrayToString(messageDigest);
            return s;
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static String convertByteArrayToString(byte[] messageDigest) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String toHexString = Integer.toHexString(0xFF & messageDigest[i]);
            if (toHexString.length() == 1) {
                toHexString = 0 + toHexString;
            }
            hexString.append(toHexString);
        }
        return hexString.toString();
    }
}

