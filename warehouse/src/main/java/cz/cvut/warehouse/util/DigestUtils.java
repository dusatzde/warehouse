package cz.cvut.warehouse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

	 public static String getHash(String plainText) {
	        try {
	            MessageDigest mdAlgorithm = MessageDigest.getInstance("MD5");
	            mdAlgorithm.update(plainText.getBytes());

	            byte[] digest = mdAlgorithm.digest();
	            StringBuffer hexString = new StringBuffer();

	            for (int i = 0; i < digest.length; i++) {
	                plainText = Integer.toHexString(0xFF & digest[i]);
	                if (plainText.length() < 2) {
	                    plainText = "0" + plainText;
	                }
	                hexString.append(plainText);
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException ex) {
	            return null;
	        }
	    }
}
