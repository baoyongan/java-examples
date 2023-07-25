import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO
 *
 * @author baoya49764
 * @date 2023/5/15 21:04
 */
public class SyncProducer {

    static String topic="user_topic";
    static String usermsg="[\n" +
            "  {\n" +
            "    \"empId\": 25889,\n" +
            "    \"userId\": \"baoya\",\n" +
            "    \"userName\": \"包牙\",\n" +
            "    \"status\": 1,\n" +
            "    \"orgId\": 401,\n" +
            "    \"position\": 0,\n" +
            "    \"userLocalCode\": \"00102985\",\n" +
            "    \"isdefaultprivilege\": 2,\n" +
            "    \"mobileNo\": \"1592225422\",\n" +
            "    \"email\": \"liuyoucai@sinochem.com.test\",\n" +
            "    \"passWord\": \"UJEX5y5b1uQsfH+9YJdBTY7DKoGp1UCB\"\n" +
            "  }\n" +
            "]";
    public static void main(String[] args) throws Exception {
        // 初始化一个producer并设置Producer group name
        DefaultMQProducer producer = new DefaultMQProducer("test_producer"); //（1）
        // 设置NameServer地址
        producer.setNamesrvAddr("10.20.36.245:9876");  //（2）
        // 启动producer
        producer.start();
//        for (int i = 0; i < 100; i++) {
            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message(topic /* Topic */,
                    "a" /* Tag */,
                    (usermsg).getBytes() /* Message body */
            );   //（3）
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = producer.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
//        }
        // 一旦producer不再使用，关闭producer
        producer.shutdown();
    }
}