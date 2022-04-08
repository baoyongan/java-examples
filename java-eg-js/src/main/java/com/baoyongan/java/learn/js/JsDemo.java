package com.baoyongan.java.learn.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.URL;
import java.util.List;

public class JsDemo {

    private ScriptEngine engine;

    public JsDemo() {
        this.engine = initEngine();
    }

    private ScriptEngine initEngine() {
        //获得脚本引擎对象，它是java和js的中介
        ScriptEngineManager sem = new ScriptEngineManager();
        return sem.getEngineByName("javascript");
    }

    public ScriptEngine getEngine() {
        return engine;
    }

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        JsDemo js=new JsDemo();
        ScriptEngine engine = js.getEngine();

        //定义变量,存储到引擎上下文中，通过java和脚本均可获取
//        engine.put("msg", "gaoqi is a good man!");
//        String str = "var user = {name:'gaoqi',age:18,schools:['清华大学','北京大学']};";
//        str += "print(user.name);";

        //执行脚本
//        engine.eval(str);
//        engine.eval("msg = 'sxt is a good school';");
//        System.out.println(engine.get("msg"));
//        System.out.print("###########################");

        //定义函数
//        engine.eval("function add(a,b){var sum = a + b; return sum;}");
        //取得调用接口
//        Invocable jsInvoke = (Invocable) engine;
        //执行脚本中定义的方法
//        Object result1 = jsInvoke.invokeFunction("add", new Object[]{13,20});
//        System.out.println(result1);

        //导入其他java包，使用其他包中的java类.若需要深入了解细节，可以详细学习Rhino的语法
        String jsCode = "importPackage(java.util); var list=Arrays.asList([\"清华大学\",\"北京大学\"]);";
        engine.eval(jsCode);

        List<String> list2 = (List<String>)engine.get("list");
        for (String temp : list2) {
            System.out.print(temp);
        }

        //执行一个js文件(我们将a.js至于项目的src下即可)
//        URL url = Demo01.class.getClassLoader().getResource("a.js");
//        FileReader fr = new FileReader(url.getPath());
//        engine.eval(fr);
//        fr.close();

    }
}
