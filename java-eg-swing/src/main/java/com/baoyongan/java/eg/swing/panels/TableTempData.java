package com.baoyongan.java.eg.swing.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

/**
 * 中心的容器
 * Created by bqct_bya on 2017/5/24.
 */
public class TableTempData{

    private DefaultTableModel model;
    private JTable table;

    public TableTempData() {
        String[] obj={"学时","日期"};
        this.model=new DefaultTableModel(obj,0);
        this.table=new JTable(model);
    }

    private void cleanData(){
        int rows=model.getRowCount();
        if(rows>1){
            for (int i = 1; i < rows; i++) {
                model.removeRow(i);
            }
        }
    }

    public void flushData(java.util.List<Map> data){
        cleanData();
        for (Map map : data) {
            String[] temp=new String[]{(String) map.get("course"), (String) map.get("date")};
            model.addRow(temp);
        }
    }

    public JTable getTable() {
        return table;
    }
}
