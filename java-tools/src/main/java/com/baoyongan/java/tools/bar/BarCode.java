package com.baoyongan.java.tools.bar;

import org.apache.commons.lang.ObjectUtils;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 条形码 - 生成
 */
public class BarCode {

    public static final int DEFAULT_DPI = 512; // 条形码默认分辨率
    public static final String IMAGE_TYPE = "image/png"; // 图片类型

    /**
     * Code128 条形码生成
     * @param customDpi 条形码分辨率，默认为512
     * @param withQuietZone 两边是否留白
     * @param height   条形码的高度
     * @param with     条形码的宽度
     * @param hideText 隐藏可读文本
     * @param text     文本
     * @return
     */
    public static byte[] generate128CodeBar(Integer customDpi,boolean withQuietZone, Double height, Double with, boolean hideText, String text) {
        Code128Bean code128Bean = new Code128Bean();
        // 分辨率
        int dpi = DEFAULT_DPI;
        if(null!=customDpi&&0!=customDpi){
            dpi=customDpi;
        }
        // 设置两边是否留白
        code128Bean.doQuietZone(withQuietZone);
        // 设置条形码 高度和宽度
        code128Bean.setBarHeight((Double) ObjectUtils.defaultIfNull(height, 9.0D));
        if (null != with)
            code128Bean.setModuleWidth(with);
        // 设置文本位置（包括是否显示）
        if (hideText) {
            code128Bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }
        // 设置图片类型
        String format = IMAGE_TYPE;

        // 创建画布
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        // 生产条形码
        code128Bean.generateBarcode(canvas,text);
        byte[] imageBytes=null;
        try {
            canvas.finish();
            imageBytes=ous.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("生成条形码失败",e);
        }finally {
            if(ous!=null){
                try {
                    ous.flush();
                    ous.close();
                } catch (IOException e) {
                    throw new RuntimeException("生成条形码失败",e);
                }
            }
        }
        return imageBytes;
    }
}
