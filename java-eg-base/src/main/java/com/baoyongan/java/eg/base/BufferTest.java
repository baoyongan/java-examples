package com.baoyongan.java.eg.base;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BufferTest {

    public static void main(String[] args) {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(11);
        byte[] str=b.array();
        for (int i = 0; i <str.length ; i++) {
            System.out.print(str[i]);
        }
    }
}
