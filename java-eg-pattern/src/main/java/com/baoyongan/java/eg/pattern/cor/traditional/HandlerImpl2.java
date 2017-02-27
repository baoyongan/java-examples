package com.baoyongan.java.eg.pattern.cor.traditional;

/**
 * 处理request2请求
 * Created by bao on 2017/2/27.
 */
public class HandlerImpl2 implements Handler {

    private Handler handler;

    public HandlerImpl2(Handler handler) {
        this.handler = handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handlerRequest(Request request) {
        if (request instanceof RequestImpl2) {
            System.out.println("HandlerImpl2 handle "+request.getClass().getSimpleName());
        }else{
            System.out.println("HandlerImpl2 can't handle "+request.getClass().getSimpleName());
            if (this.handler != null) {
                handler.handlerRequest(request);
            }
        }

    }

}
