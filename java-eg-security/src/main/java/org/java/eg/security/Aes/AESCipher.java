package org.java.eg.security.Aes;

import org.apache.commons.codec.binary.Base64;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESCipher {
	private static String CHARSET = "UTF-8";
//	private static String TRANSFORMATION = "AES/ECB/PKCS5Padding";
	private static String TRANSFORMATION = "AES/OFB/NoPadding";
	private static SecretKey secretKey;
	static SecureRandom rnd = new SecureRandom();

//	static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(16));
	static {
		Properties properties = new Properties();
		InputStream inStream = AESCipher.class.getResourceAsStream("/crypt-common-config.properties");

		try {
			properties.load(inStream);
			String keyString = properties.getProperty("string.key");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed((keyString.getBytes()));
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, random);
			secretKey = kgen.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用AES加密明文
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encrypt(String plainText) {
		try {
			Cipher encryptCipher = Cipher.getInstance(TRANSFORMATION);
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] resultBytes = encryptCipher.doFinal(plainText.getBytes(CHARSET));
			return Base64.encodeBase64String(resultBytes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用AES解密明文
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decrypt(String cipherText) {
		try {
			Cipher decryptCipher = Cipher.getInstance(TRANSFORMATION);
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] cipherBytes = Base64.decodeBase64(cipherText);
			byte[] resultBytes = decryptCipher.doFinal(cipherBytes);
			return new String(resultBytes, CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		long s=System.currentTimeMillis();
		String content = "232_"+s;
//		String key = "7RV8kqVc";
		System.out.println("content:" + content);
		String s1 = AESCipher.encrypt(content);
		System.out.println("s1:" + s1);
		System.out.println("s2:"+AESCipher.decrypt(s1));
	}

}
