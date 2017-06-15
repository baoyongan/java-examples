import com.bya.remote.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by bqct_bya on 2017/6/11.
 */
public class App {
    public static void main(String[] args) {
        try  {
        ApplicationContext app= new ClassPathXmlApplicationContext("classpath:spring-application.xml");
//        HessianProxyFactoryBean hessionService= (HessianProxyFactoryBean) app.getBean("helloService");
        // "helloService",HessianProxyFactoryBean.class
            HelloWorld helloWorld=(HelloWorld) app.getBean("helloService");
        helloWorld.sayHello("baozi");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
