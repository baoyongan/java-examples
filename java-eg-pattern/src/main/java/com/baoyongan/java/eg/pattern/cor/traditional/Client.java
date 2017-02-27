package com.baoyongan.java.eg.pattern.cor.traditional;

/**
 * Created by bao on 2017/2/27.
 */
public class Client {
    public static void main(String[] args) {
        Handler handler1= new HandlerImpl1(null );
        Handler handler2=new HandlerImpl2(handler1);
        Handler handler3=new HandlerImpl3(handler2);

        handler3.handlerRequest(new RequestImpl3());
        System.out.println("-----------------------");
        handler3.handlerRequest(new RequestImpl2());
        System.out.println("-----------------------");
        handler3.handlerRequest(new RequestImpl1());
        System.out.println("-----------------------");
    }
}
