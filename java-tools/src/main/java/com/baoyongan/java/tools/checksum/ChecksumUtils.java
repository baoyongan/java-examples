package com.baoyongan.java.tools.checksum;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * 校验和工具类
 *
 * @author baoya49764
 * @date 2023/7/12 20:07
 */
public class ChecksumUtils {

    /**
     * 获取字符串的CRC32校验和
     * @param data
     * @return
     */
    public static long stringByCrc32(String data){
        CRC32 crc32= new CRC32();
        crc32.update(data.toString().getBytes(StandardCharsets.UTF_8));
        return crc32.getValue();
    }

    /**
     * 使用CRC32 获取文件校验和（使用缓存，控制内存占用）
     * @param stream
     * @param bufferSize
     * @return
     * @throws IOException
     */
    public static long getChecksumCRC32(InputStream stream, int bufferSize)
            throws IOException {
        CheckedInputStream checkedInputStream = new CheckedInputStream(stream, new CRC32());
        byte[] buffer = new byte[bufferSize];
        while (checkedInputStream.read(buffer, 0, buffer.length) >= 0) {}
        return checkedInputStream.getChecksum().getValue();
    }

    public static void main(String[] args) throws IOException {
        // 普通String 校验和
        JSONObject content =new JSONObject(new LinkedHashMap<>());
        content.put("AssetCode", "10");
        content.put("Trustee", "20");
        content.put("SenderUrl", "30");
        content.put("Sequence", "40");
        content.put("SystemID", "TODO");
        content.put("QueryDate", "50");
        content.put("AccountType", "60");
        content.put("AccountCode", "70");
        content.put("AccountName", "80");
        System.out.println("===========content=========");
        System.out.println("before crc32:"+content);
        System.out.println("crc32: "+stringByCrc32(content.toString()));
        // 文件校验和
        System.out.println(getChecksumCRC32(new FileInputStream(new File("C:\\Users\\hspcadmin\\java_error_in_idea64_22240.log")),1024));
    }
}
