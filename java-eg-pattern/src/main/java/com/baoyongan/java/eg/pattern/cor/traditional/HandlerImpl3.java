package com.baoyongan.java.eg.pattern.cor.traditional;

/**
 * 处理request3请求
 * Created by bao on 2017/2/27.
 */
public class HandlerImpl3 implements Handler {

    private Handler handler;

    public HandlerImpl3(Handler handler) {
        this.handler = handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handlerRequest(Request request) {
        if (request instanceof RequestImpl3) {
            System.out.println("HandlerImpl3 handle "+request.getClass().getSimpleName());
        }else{
            System.out.println("HandlerImpl3 can't handle "+request.getClass().getSimpleName());
            if (this.handler != null) {
                handler.handlerRequest(request);
            }
        }

    }

}
