package com.baoyongan.java.tools.csv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class CsvController {

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        try {
            String path = "C:\\Users\\zpf\\Desktop\\1_220专案（1）_通话记录_话单.csv";

            /**
             * 写入csv结束，写出流
             */
            File tempFile = new File(path);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[10240];
            File fileLoad = new File(tempFile.getCanonicalPath());
            response.reset();
            response.setContentType("application/csv");
            response.setHeader("content-disposition", "attachment; filename=通话记录模板.csv");
            long fileLength = fileLoad.length();
            String length1 = String.valueOf(fileLength);
            response.setHeader("Content_Length", length1);
            FileInputStream in = new FileInputStream(fileLoad);
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n); //每次写入out1024字节
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public boolean importFile(MultipartFile multipartFile) {
//
//        if (multipartFile.isEmpty()) throw new BusinessException("上传内容为空");
//        boolean successful;
//        try {
//
//            // 用来保存数据
//            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
//            List<ThjlEntity> tempList = new ArrayList<>();
//            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
//            CsvReader reader = new CsvReader(multipartFile.getInputStream(), ',', Charset.forName("GB2312"));
//            // 跳过表头 如果需要表头的话，这句可以忽略
//            reader.readHeaders();
//            // 逐行读入除表头的数据
//            while (reader.readRecord()) {
//                System.out.println(reader.getRawRecord());
//                String s = reader.get(0);
//                csvFileList.add(reader.getValues());
//
//                ThjlEntity thjlEntity = new ThjlEntity();
//                thjlEntity.setThsj(reader.get(0));
//                thjlEntity.setYhhm(reader.get(1));
//                thjlEntity.setBdxm(reader.get(2));
//                thjlEntity.setBdsfz(reader.get(3));
//                thjlEntity.setBddz(reader.get(4));
//                thjlEntity.setYhgsd(reader.get(5));
//                thjlEntity.setImei(reader.get(6));
//                thjlEntity.setDfhm(reader.get(7));
//                thjlEntity.setDdxm(reader.get(8));
//                thjlEntity.setDdsfz(reader.get(9));
//                thjlEntity.setDddz(reader.get(10));
//                thjlEntity.setDdgsd(reader.get(11));
//                thjlEntity.setHjlx(reader.get(12));
//                thjlEntity.setThsc(reader.get(13));
//                thjlEntity.setThszd(reader.get(14));
//                thjlEntity.setLac(reader.get(15));
//                thjlEntity.setCi(reader.get(16));
//                thjlEntity.setJd(reader.get(17));
//                thjlEntity.setWd(reader.get(18));
//                thjlEntity.setJzdz(reader.get(19));
//
//
//                tempList.add(thjlEntity);
//
//            }
//            reader.close();
//
//
//            successful = this.saveBatch(tempList);
//
//        } catch (Exception e) {
//            log.error("系统异常",e);
//            throw new BusinessException(e.getMessage());
//        }
//
//        return successful;
//    }
}
