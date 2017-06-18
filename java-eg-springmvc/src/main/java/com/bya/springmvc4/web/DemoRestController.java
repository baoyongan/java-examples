package com.bya.springmvc4.web;

import com.bya.springmvc4.domain.DemoObj;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bao on 2017/6/18.
 */
@RestController // 返回数据的时候不需要@ResponseBody
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping(value = "/getjson",produces = {"application/json;charset=UTF-8"}) // 返回的数据类型是json
    public DemoObj getjson(DemoObj obj){
        return  new DemoObj(obj.getId()+1,obj.getName()+"yy");
    }

    @RequestMapping(value = "/getxml",produces = {"application/xml;charset=UTF-8"}) // 返回的数据类型是xml
    public DemoObj getxml(DemoObj obj){
        return  new DemoObj(obj.getId()+1,obj.getName()+"yy");
    }
}
