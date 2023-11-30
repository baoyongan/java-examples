package com.baoyongan.java.eg.base.generic_ch;

/**
 * 实现泛型接口的示例
 * @param <K>
 * @param <V>
 */
public class OrderedPair<K, V> implements Pair<K, V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey()	{ return key; }
    public V getValue() { return value; }

    public static void main(String[] args) {
        Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
        Pair<String, String>  p2 = new OrderedPair<>("hello", "world");
        Pair<String, BoxGeneric<String>> p3 = new OrderedPair<>("Bo", new BoxGeneric<>("312"));
        boolean compare = GenericMethod.compare(p1, p1);
    }

}
