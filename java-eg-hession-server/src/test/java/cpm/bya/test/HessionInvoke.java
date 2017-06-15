package cpm.bya.test;

import com.bya.remote.HelloWorld;
import com.caucho.hessian.client.HessianProxyFactory;

import java.net.ConnectException;

/**
 * Created by bqct_bya on 2017/6/11.
 */
public class HessionInvoke {
    public static void main(String[] args) {

        try {
            String url = "http://localhost:8589/HelloService";
            HessianProxyFactory factory = new HessianProxyFactory();
            HelloWorld helloService = (HelloWorld) factory.create(
                    HelloWorld.class, url);
            helloService.sayHello("张三");
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }

}
