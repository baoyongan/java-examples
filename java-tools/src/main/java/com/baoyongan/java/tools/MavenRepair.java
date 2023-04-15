package com.baoyongan.java.tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MavenRepair {

    public static void main(String[] args) throws IOException {

//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\hundsun");
        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\org\\springframework\\boot");
//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\lowagie");
//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\oracle");
//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\hundsun\\jrescloud");
//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\hundsun\\jres3");
//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\hundsun\\broker");
//        repair("D:\\hundsun\\code\\CRM5.0\\maven-ku\\com\\hundsun\\hswealth");

    }

    private static void repair(String path) throws IOException {
        File file=new File(path);
        File[] files = file.listFiles();
        for (File f: files) {
            if(f.isDirectory()){
                repair(f.getPath());
            }else {
                // 可以直接删除 _remote.repositories，这样既不会再去服务器下载
//                String remote=f.getParentFile().getAbsolutePath()+"/_remote.repositories";
//                String remote_bak=f.getParentFile().getAbsolutePath()+"/_remote.repositories.bak";
//                File remoteFile = new File(remote);
//                if(remoteFile.exists()){
//                    System.out.printf("move form %s to %s \r\n",remoteFile,remote_bak);
//                    FileUtils.moveFile(remoteFile,new File(remote_bak));
//                }
                if(f.getName().endsWith(".lastUpdated")){
                    System.out.println("delete:"+f.getName());
                    f.delete();
                    String remote=f.getParentFile().getAbsolutePath()+"/_remote.repositories";
                    String remote_bak=f.getParentFile().getAbsolutePath()+"/_remote.repositories.bak";
                    File remoteFile = new File(remote);
                    if(remoteFile.exists()){
                        System.out.printf("move form %s to %s \r\n",remoteFile,remote_bak);
                        FileUtils.moveFile(remoteFile,new File(remote_bak));
                    }
                }
            }
        }
    }
}
