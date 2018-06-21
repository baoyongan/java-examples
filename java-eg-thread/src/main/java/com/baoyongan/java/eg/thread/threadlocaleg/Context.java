package com.baoyongan.java.eg.thread.threadlocaleg;

public class Context {
    private String id;

    private String threadName;

    public Context(String id, String threadName) {
        this.id = id;
        this.threadName = threadName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "Context{" +
                "id='" + id + '\'' +
                ", threadName='" + threadName + '\'' +
                '}';
    }
}
