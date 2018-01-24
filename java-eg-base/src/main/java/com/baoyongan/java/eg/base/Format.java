package com.baoyongan.java.eg.base;

import java.util.Date;

public class Format {

    public static void main(String[] args) {

        /*
        常规类型、字符类型和数值类型的格式说明符的语法如下：
        %[argument_index$][flags][width][.precision]conversion
        可选的 argument_index 是一个十进制整数，用于表明参数在参数列表中的位置。第一个参数由 "1$" 引用，第二个参数由 "2$" 引用，依此类推。
        可选 flags 是修改输出格式的字符集。有效标志集取决于转换类型。
        可选 width 是一个非负十进制整数，表明要向输出中写入的最少字符数。
        可选 precision 是一个非负十进制整数，通常用来限制字符数。特定行为取决于转换类型。
        所需 conversion 是一个表明应该如何格式化参数的字符。给定参数的有效转换集取决于参数的数据类型。

        用来表示日期和时间类型的格式说明符的语法如下：
        %[argument_index$][flags][width]conversion
        可选的 argument_index、flags 和 width 的定义同上。
        所需的 conversion 是一个由两字符组成的序列。第一个字符是 't' 或 'T'。第二个字符表明所使用的格式。这些字符类似于但不完全等同于那些由 GNU date 和 POSIX strftime(3c) 定义的字符。

        与参数不对应的格式说明符的语法如下：
        %[flags][width]conversion
        可选 flags 和 width 的定义同上。

        所需的 conversion 是一个表明要在输出中所插内容的字符。
         */
        System.out.format("%d", 12);
        System.out.println();
        System.out.format("% 4d", 12);
        System.out.println();
        System.out.format("% 10d", 12);
        System.out.println();
        System.out.format("%010d", 12);
        System.out.println();

        String s2 = String.format("%1$tY-%1$tm-%1$te", new Date());
        System.out.println(s2);

        /**
         * 查看JDK文档得知,String.format方法的第一个参数是有个公式可以套的
         %[argument_index$][flags][width][.precision]conversion
         这里我们只要牢记这个公式就可以,下面说下每个颜色所代表的含义
         argument_index: 可选,是一个十进制整数，用于表明参数在参数列表中的位置。第一个参数由 "1$" 引用，第二个参数由 "2$" 引用，依此类推。
         flags: 可选,用来控制输出格式
         width: 可选,是一个正整数，表示输出的最小长度
         precision:可选,用来限定输出字符数
         conversion:必须,用来表示如何格式化参数的字符
         */
        // 我们发现[argument_index$][flags][width][.precision]这些部分全部都省略掉了 只留下一个必须的conversion,在这里conversion就是"s",百分号%是固定不变的
        System.out.println(String.format("我的名字叫%s", "小明")); // 打印:我的名字叫小明
        // 这里会按顺序分别把小明,小方填入到对应的%s中. 如果我们要把小方填在前面,小明填在后面,那该怎么做呢,[argument_index$]就派上用场了
        System.out.println( String.format("我叫%2$s,她叫%1$s", "小明","小方")); // 我叫小方,她叫小明
        /*
        conversion可以填s,那还有什么其它字母可以填呢,当然有的比如
        o:结果被格式化为八进制整数
        x:结果被格式化为十六进制
        d:结果被格式化为十进制整数
         */
        System.out.println(String.format("%o", 8)); // 10
        System.out.println(String.format("%x", 16)); // 10
        /*
        flag是用来控制输出格式的,比如左对齐,金额用逗号隔开等
        width:表示最小宽度
         */
        System.out.println(String.format("%1$,d", 12302562)); // 12,302,562
        // 这里多出一个逗号",",它就是flag,用于金额千分位隔开,当然写成"%,d"也是可以的
        System.out.println(String.format("%1$08d", 123456));// 00123456
//        这里0就是flag,表示结果将用零来填充,8就是width,表示最少要8位,d是conversion
//        至于其它的flag可以查阅JDK文档
        //接下来说下[.precision]
        //这个单词翻译下是精度的意思,我们发现了前面有个小数点".",因此不难联想到这个是关于浮点数类型的
        //只有当传入的数据是浮点数时这个才有用,整数或者日期类型的数据都不能用
        //比如我想要四舍五入保留两位小数,那么我可以这么写:
        System.out.println( String.format("%1$.2f", 12.12555));// 12.13
        //这里f表示传入的数字是浮点型,如果传入的是整数,或者把f改成d都会抛出异常,JDK文档中有明确说明
        //对于浮点转换 'e'、'E' 和 'f'，精度是小数点分隔符后的位数。如果转换是 'g' 或 'G'，那么精度是舍入计算后所得数值的所有位数。如果转换是 'a' 或 'A'，则不必指定精度。
        //对于字符、整数和日期/时间参数类型转换，以及百分比和行分隔符转换，精度是不适用的；如果提供精度，则会抛出异常。
    }
}
