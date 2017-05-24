package com.baoyongan.java.eg.swing.panels;

import com.baoyongan.java.eg.swing.frames.PreCourseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 南测的容器
 * Created by bqct_bya on 2017/5/24.
 */
public class SouthPanel extends JPanel{
    private PreCourseFrame frame;
    public SouthPanel(PreCourseFrame frame) {
        this.frame=frame;
        init();
    }

    private void init(){
        JButton newRuleButton=new JButton("新增课时规则");

        JButton createCourseButton=new JButton("生成课时");

        add(newRuleButton);
        add(createCourseButton);

        newRuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WestPanel westPanel=frame.getWestPanel();
                westPanel.addRule();
                westPanel.revalidate();
            }
        });

        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.doCrouse();
            }
        });

    }

}
