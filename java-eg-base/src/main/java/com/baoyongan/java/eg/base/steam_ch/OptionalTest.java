package com.baoyongan.java.eg.base.steam_ch;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Optional<Integer> op = Optional.of(0);

        op.ifPresent((t) -> {
            System.out.println("打印 Optional 的 值：" + t);
        });
        if (op.filter(t -> t > 10).isPresent()) {
            System.out.println(op.get());
        } else {
            System.out.println("可选值 不存在或者不符合大于10的条件");
        }
        Optional<Integer> newOp = op.map(t -> t*0);
        if (newOp.isPresent()) {
            System.out.println("打印" + op.get() + "的映射值：" + newOp.get());
        } else {
            System.out.println("映射结果为空，可能是原值不存在，也有可能是映射值为 null");
        }

        System.out.println(op.equals(newOp));

        Optional<Integer> newOop = op.flatMap(t -> {
            if (t <= 0)
                return Optional.empty();
            else
                return op;
        });
        if (newOop.isPresent()) {
            System.out.println("打印" + newOop.get() + "的映射值：" + newOop.get());
        } else {
            System.out.println("映射结果为空，可能是原值不存在，也有可能是映射值为 null");
        }

        System.out.println("取值：");
        System.out.println(op.orElse(1));

        System.out.println(op.orElseGet(() -> 1));

        System.out.println(op.orElseThrow(() -> new RuntimeException("该值不存在")));

        Optional<Long> opl=Optional.of(0L);
        System.out.println(op.equals(opl));
    }
}
