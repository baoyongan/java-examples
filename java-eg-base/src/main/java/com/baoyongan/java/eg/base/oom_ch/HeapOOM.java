package com.baoyongan.java.eg.base.oom_ch;

import java.util.ArrayList;
import java.util.List;


public class HeapOOM {

    static class OOMObject{
//        Object
    }

    /**
     * VM args:-Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
