package com.notice.vo;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 发送消息
 */
public class Message {

    private String content;

    private Map<String,String> names;

    private Date date=new Date();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //这个方法是用来拼装规范信息的，每一条信息会说明是谁发送过来和具体的时间。
    //一个对象要转成JSON字符串就需要用到Gson。在Maven添加相关的依赖。
    public void setContent(String name,String msg) {
        this.content = name+" "+DateFormat.getDateTimeInstance().format(date) +":<br/> "+msg;
    }

    public Map<String, String> getNames() {
        return names;
    }
    public void setNames(Map<String, String> names) {
        this.names = names;
    }

     /**
      * 
      * @return 转化为字符串
      */
    public String toJson(){
        return gson.toJson(this);
    }

    private static Gson gson=new Gson();
    /**
     * 
     * @param content  消息内容
     * @param names    map中就是   (sessionid：张三)
     */
    public Message(String content, Map<String, String> names) {
        this.content = content;
        this.names = names;
    }

    public Message() {
    }
}
