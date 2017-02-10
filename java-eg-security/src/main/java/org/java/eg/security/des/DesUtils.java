package org.java.eg.security.des;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * des 加、解密
 * 
 * @author bya
 *
 */
public class DesUtils {

	private static final String CHARSET = "UTF-8";
	private static final String ALGORITHM = "DES";
	private static final String TRANSFORMATION = "DES";// DES/ECB/PKCS5Padding
	private static final String KEY = "B6865811";
	private static SecretKey secretkey = null;

	private static Key getKey() {
		if (secretkey == null) {
			byte[] key = null;
			try {
				key = KEY.getBytes(CHARSET);
				secretkey = new SecretKeySpec(key, ALGORITHM);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return secretkey;
	}

	/**
	 * 加密
	 */
	public static String encrypt(String source) {
		String result = null;
		byte[] input = null;
		try {
			byte[] center = source.getBytes(CHARSET);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, getKey());
			input = cipher.doFinal(center);
			// 加密字节数组base64转换String
			result = Base64.encodeBase64String(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解密
	 */
	public static String decrypt(String source) {
		String result = null;
		byte[] dissect = null;
		try {
			// 密文字符串base64转换为密文字节数组
			byte[] input = Base64.decodeBase64(source);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, getKey());
			dissect = cipher.doFinal(input);
			result = new String(dissect, CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
