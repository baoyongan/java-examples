package com.baoyongan.java.eg.base;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author LingLee
 *
 */
public class MarkLogoIcon {
    public static void markByIcon(String iconPath,String srcImagePath,String targetPath){
        markByIcon(iconPath, srcImagePath, targetPath,null);
    }
    public static void markByIcon(String iconPath,String srcImagePath,String tagetPath,Integer degree){
        OutputStream os=null;
        try{
            Image srcImage=ImageIO.read(new File(srcImagePath));
            BufferedImage buffImg=new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferedImage.TYPE_INT_BGR);
            Graphics2D g=buffImg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImage.getScaledInstance(srcImage.getWidth(null), srcImage.getHeight(null), Image.SCALE_SMOOTH),
                    0,0,null);

            if(degree!=null){
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth(null)/2, (double) buffImg.getHeight(null)/2);
            }
            ImageIcon imageIcon=new ImageIcon(iconPath);
            Image img=imageIcon.getImage();
            float alpha=0.5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
            g.drawImage(img, 100, 100, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

            g.dispose();

            os = new FileOutputStream(tagetPath);

            //生成图片
            ImageIO.write(buffImg, "JPG", os);
            System.out.println("图片水印添加成功。。。");
        }catch(Exception e){
            e.printStackTrace();
        }finally{try{if(null!=os){os.close();}}catch(Exception e){e.printStackTrace();}}
    }
    public static void main(String[] args){
        String srcImagePath="d:/tmp/wen.jpg";
        String targetImagePath="d:/tmp/wen1.jpg";
        String iconPath="d:/tmp/timg.jpg";
        String targetImagePath2="d:/tmp/wen2.jpg";
        markByIcon(iconPath, srcImagePath, targetImagePath);
        markByIcon(iconPath, srcImagePath, targetImagePath2,-45);
    }
}
