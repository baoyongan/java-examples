package org.java.eg.security;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class EncodeAndDecodeTest {

    @Test
    public void base64(){
        String str1="hello";
        byte[] data=Base64.encodeBase64(str1.getBytes());
        String str2=new String(Base64.decodeBase64(data));
        System.out.println(str1);
        System.out.println(str2);
        Assert.assertEquals(str1,str2);
    }

    @Test
    public void hex() {
        String str1 = "hello";
        char[] data = Hex.encodeHex(str1.getBytes());
        String str2 = null;
        try {
            str2 = new String(Hex.decodeHex(data));
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(str1, str2);
    }
}
