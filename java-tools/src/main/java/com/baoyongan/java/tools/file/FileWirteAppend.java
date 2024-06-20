package com.baoyongan.java.tools.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 追加写入文件
 */
public class FileWirteAppend {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        File output = new File("C:\\Users\\hspcadmin\\Desktop\\output.txt");
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < 350000; i++) {
            if (i % 5000 == 0) {
                writeData(data, output, executorService);
                data.clear();
            }
            Map<String, String> tmp = new HashMap<>(4);
            tmp.put("name", "s" + i);
            tmp.put("age", "" + i);
            data.add(tmp);
        }
        // 执行剩余的
        if (data.size()>0) {
            writeData(data, output, executorService);
            data.clear();
        }
        long end = System.currentTimeMillis();
        System.out.printf("花费了 %d s",(end-start)/1000);
        executorService.shutdown();
        System.out.println("执行完成");
    }

    private static void writeData(List<Map<String, String>> data, File output, ExecutorService executorService) throws InterruptedException, ExecutionException {

        List<Future<String>> futures = executorService.invokeAll(data.stream().map(t -> {
                    return new Callable<String>() {

                        @Override
                        public String call() throws Exception {
                            return writeData(output, t.get("name") + t.get("age"));
                        }
                    };


                }).collect(Collectors.toList())
        );
        for (Future<String> fu :
                futures) {
            String result = fu.get();
            if (!"ok".equals(result)) {
                System.err.println("任务执行失败");
            }
        }
    }

    private synchronized static String writeData(File output, String line) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(output, true);
            writer.write(line+"\n");
        } catch (IOException e) {
            throw new RuntimeException("写入文件失败", e);
        } finally {
            if (null !=writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "ok";
    }
}
