package com.baoyongan.java.eg.base.io_and_nio_ch;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.GZIPInputStream;

public class FileTool {

    public static void main(String[] args) {

        String baseroot = "D:/servers/apache-tomcat-7.0.92-for-config/webapps/filebase/photos/tempFile";
        //
        String fwsName = "宜宾市锦灵商贸有限公司";
        String baseFileDirPath = baseroot + File.separator + fwsName + "_" + System.currentTimeMillis();
        File baseFile = new File(baseFileDirPath);
        // 创建目录
        if (baseFile.mkdir()) {
            System.out.println("创建目录成功");
        }
        String srcFilePath = "D:\\servers\\apache-tomcat-7.0.92-for-config\\webapps\\photobase\\photos\\jmapply\\2020-07-06\\B200706002330\\043a79a761ff4ea6976e5bb62dbe9990_.jpg";
        String destFileName = "营业执照照片-1" + srcFilePath.substring(srcFilePath.lastIndexOf("."));
        String destFilePath = baseFileDirPath + File.separator + destFileName;
        System.out.println(destFilePath);
        // 文件拷贝
        try {
            FileUtils.copyFile(new File(srcFilePath), new File(destFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 压缩zip
        try {
            zip(baseFileDirPath, baseFileDirPath + ".zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 删除原目录
        try {
            FileUtils.deleteDirectory(baseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zip(String srcDir, String targetFile) throws IOException {
        OutputStream fos=null;
        OutputStream bos=null;
        ArchiveOutputStream aos=null;
        try {
            fos = new FileOutputStream(targetFile);
            bos = new BufferedOutputStream(fos);
            aos = new ZipArchiveOutputStream(bos);

            Path dirPath = Paths.get(srcDir);
            ArchiveOutputStream finalAos = aos;
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    ArchiveEntry entry = new ZipArchiveEntry(dir.toFile(), dirPath.relativize(dir).toString());
                    finalAos.putArchiveEntry(entry);
                    finalAos.closeArchiveEntry();
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    ArchiveEntry entry = new ZipArchiveEntry(
                            file.toFile(), dirPath.relativize(file).toString());
                    finalAos.putArchiveEntry(entry);
                    FileInputStream fileIs = new FileInputStream(file.toFile());

                    IOUtils.copy(fileIs, finalAos);
                    if (null != fileIs) {
                        fileIs.close();
                    }
                    finalAos.closeArchiveEntry();
                    return super.visitFile(file, attrs);
                }
            });
        }catch (IOException e){
            throw e;
        }finally {
            if (null != aos) {
                try {
                    aos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
