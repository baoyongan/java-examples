package com.baoyongan.java.eg.base.generic_ch;

/**
 * 泛型接口类
 * @param <K>
 * @param <V>
 */
public interface Pair<K, V> {
    public K getKey();
    public V getValue();
}
