package com.baoyongan;

import com.baoyongan.java.eg.swing.frames.PreCourseFrame;

import java.awt.*;

/**
 * APP
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth=screenSize.width;
        // 课程窗口
        new PreCourseFrame(screenHeight,screenWidth);

    }
}
