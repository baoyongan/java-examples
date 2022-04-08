package com.baoyongan.java.tools.csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ReadAndWriterCsvFlie {

    // 需要写入的 csv 文件路径
    public static final String WRITE_CSV_FILE_PATH = "D:/tmp/write_test.csv";

    /**
     * 生成 csv 文件
     */
    public static void writeCsvFile(String writeCsvFilePath) {
        // 创建 CSV Writer 对象, 参数说明（写入的文件路径，分隔符，编码格式)
        CsvWriter csvWriter = new CsvWriter(writeCsvFilePath,',', Charset.forName("GBK"));

        try {
            // 定义 header 头
            String[] headers = {"订单号", "用户名", "支付金额"};
            // 写入 header 头
            csvWriter.writeRecord(headers);

            // 写入一千条记录
            for (int i = 0; i < 1000; i++) {
                String orderNum = UUID.randomUUID().toString();
                String userName = "用户" + i;
                String payMoney = String.valueOf(i);
                // 写入行
                csvWriter.writeRecord((String[]) Arrays.asList(orderNum, userName, payMoney).toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            csvWriter.close();
        }
    }

    /**
     * 读取 csv 文件
     */
    public static void readCsvFile(String readCsvFilePath) {
        // 缓存读取的数据
        List<String[]> content = new ArrayList<>();

        try {
            // 创建 CSV Reader 对象, 参数说明（读取的文件路径，分隔符，编码格式)
            CsvReader csvReader = new CsvReader(readCsvFilePath, ',', Charset.forName("GBK"));
            // 跳过表头
            csvReader.readHeaders();

            // 读取除表头外的内容
            while (csvReader.readRecord()) {
                // 读取一整行
                String line = csvReader.getRawRecord();
                System.out.println(line);

                content.add(csvReader.getValues());
            }
            csvReader.close();

            for (int row = 0; row < content.size(); row++) {
                // 读取第 row 行，第 0 列的数据
                String orderNum = content.get(row)[0];
                System.out.println("==> orderNum: " + orderNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readCsvFile(WRITE_CSV_FILE_PATH);
//        writeCsvFile(WRITE_CSV_FILE_PATH);
    }
}
