package com.baoyongan.java.eg.base.error_ch;

public class Operation {

    public static void main(String[] args) {
        int j=0;
        j=++j;
        System.out.println(j);

        /**
         * j++   运算完成之后，工作内存 仍然是原来load进来的j=0 副本。没有重新从主内存load
         *  Code:
         0: iconst_0
         1: istore_1
         2: iload_1
         3: iinc          1, 1
         6: istore_1
         7: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         10: iload_1
         11: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
         14: return
         */

        /**
         * ++j 运算完成之后，工作内存 会重新load进来的j 副本
         *  Code:
         0: iconst_0
         1: istore_1
         2: iinc          1, 1
         5: iload_1
         6: istore_1
         7: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         10: iload_1
         11: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
         14: return
         */

    }
}
