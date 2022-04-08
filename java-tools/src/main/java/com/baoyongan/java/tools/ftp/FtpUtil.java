package com.baoyongan.java.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

public class FtpUtil {

    /**
     * 获取FTPClient对象
     *
     * @param ftpHost     FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort     FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort, String Function) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println(Function + ":FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param ftpHost     FTP服务器hostname
     * @param ftpUserName 账号
     * @param ftpPassword 密码
     * @param ftpPort     端口
     * @param ftpPath     FTP服务器中文件所在路径 格式： ftptest/aa
     * @param fileName    ftp文件名称
     * @param input       文件流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort, String ftpPath, String uploadFileName, InputStream input) {
        boolean issuccess = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort, "上传");
            reply = ftpClient.getReplyCode();
            System.out.println("上传reply:" + reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return issuccess;
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); // 传输类型
            if (!ftpClient.changeWorkingDirectory(ftpPath)) {
                ftpClient.changeWorkingDirectory("/");
                String[] dirs = ftpPath.split("/");
                for (String dir : dirs) {
                    ftpClient.makeDirectory(dir);
                    ftpClient.changeWorkingDirectory(dir);
                }
            }
            ftpClient.enterLocalPassiveMode();// 大部分用在Linux上
            if (!ftpClient.storeFile(new String(uploadFileName.getBytes("GBK"), "iso-8859-1"), input)) {
                return issuccess;
            }
            input.close();
            ftpClient.logout();
            issuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return issuccess;
    }

    /*
     * 从FTP服务器下载文件
     *
     * @param ftpHost FTP IP地址
     *
     * @param ftpUserName FTP 用户名
     *
     * @param ftpPassword FTP用户名密码
     *
     * @param ftpPort FTP端口
     *
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     *
     * @param localPath 下载到本地的位置 格式：H:/download
     *
     * @param fileName 文件名称
     */

    public static boolean downloadFtpFile(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort,
                                          String ftpPath, String downloadPath, String uploadFileName, String downloadFileName) {
        boolean issuccess = false;
        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort, "下载");
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode(); // 大部分用在Linux上
            ftpClient.changeWorkingDirectory(ftpPath); // 切换到ftp的服务器路径
            File localFile = new File(downloadPath + File.separatorChar + downloadFileName);// 设定要下载的路径
            System.out.println("文件目录:" + localFile);
            OutputStream os = new FileOutputStream(localFile); // 实例化
            if (ftpClient.retrieveFile(new String(uploadFileName.getBytes("GBK"), "iso-8859-1"), os)) { // 将文件写入到下载的文件中
                issuccess = true;
            }
            os.close();
            ftpClient.logout();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + ftpPath + "下载");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }
        return issuccess;
    }
}