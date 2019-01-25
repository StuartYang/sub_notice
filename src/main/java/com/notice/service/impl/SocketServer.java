package com.notice.service.impl;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Value: URI映射  也就是我们的用户名
// ws = new WebSocket("ws://127.0.0.1:8086/socketServer/"+$("#username").val());
@ServerEndpoint(value = "/socketServer/{userid}")
@Component
public class SocketServer {
    //创建session
    private Session session;
    //session线程池
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();
    //创建session的id  map
    private static Map<String,String> sessionIds = new HashMap<String,String>();

    /**
     * 用户连接时触发  这个是自动触发的不用请求地址
     * @param session
     * @param userid
     */
    @OnOpen
    public void open(Session session,@PathParam(value="userid")String userid){
        //获得当前用户的session
        this.session = session;
        //key是用户名，值是session userid是用户名    相当于    sessionPool.put(刘宇:sessionid)
        sessionPool.put(userid, session);
        //sessionid是唯一的，每连接一次，会有一个session会话，当作key，值是路径传参，是input表单传过来的。用户名
        //sessionid的id是唯一的   用来标注是谁，哪个用户
        sessionIds.put(session.getId(), userid);


/*		String sessionid = session.getId();
		String a =sessionIds.get(session.getId());
		String userids =userid;
		System.out.println("sessionid="+sessionid+a+":"+userids);*/
    }

    /**
     * 收到信息时触发，客户端发送消息的时候触发
     * @param message  前端代码：在sendMsg()方法里面获取到文本内容，调用send()方法，ws.send($("#writeMsg").val());
     */
    @OnMessage
    public void onMessage(String message){
        //需要调用sendMessage()
        //sessionIds.get(session.getId()) 根据当前会话的id，从map中去取对应的用户名
        //这个是获取当前的用户名称，因为你存的时候就是这么在map中存储的
        System.out.println("收到客户端消息的时候消息的时候触发");
        sendMessage(sessionIds.get(session.getId())+"<--"+message,"niezhiliang9595");
	/*	String s =sessionIds.get(session.getId());
		String ss = "<--"+message;
		System.out.println(s+ss);*/
        System.out.println("发送人:"+sessionIds.get(session.getId())+"-----"+"内容:"+message);
    }

    /**
     * 连接关闭触发
     */
    @OnClose
    public void onClose(){
        sessionPool.remove(sessionIds.get(session.getId()));
        sessionIds.remove(session.getId());
    }

    /**
     * 发生错误时触发
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     *信息发送的方法
     * @param message   刘宇<--message
     * @param userId    niezhiliang9595
     */
    public synchronized static void sendMessage(String message,String userId){
        System.out.println("信息发送的时候");
        //key是用户名，值是session
        Session s = sessionPool.get(userId);
        if(s!=null){
            try {
                //websocket  session发送文本消息的方法
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前连接数
     * @return
     */
    public synchronized static int getOnlineNum(){
        //Java String.contains()方法，当且仅当此字符串包含指定的char值序列时，返回true。
        if(sessionIds.values().contains("niezhiliang9595")) {

            return sessionPool.size()-1;
        }
        return sessionPool.size();
    }

    /**
     * 获取在线用户名以逗号隔开
     * @return
     */
    public synchronized static String getOnlineUsers(){
        StringBuffer users = new StringBuffer();
        for (String key : sessionIds.keySet()) {//niezhiliang9595是服务端自己的连接，不能算在线人数
            if (!"niezhiliang9595".equals(sessionIds.get(key)))
            {
                users.append(sessionIds.get(key)+",");
            }
        }
        return users.toString();
    }

    /**
     * 信息群发
     * @param msg
     */
    public synchronized static void sendAll(String msg) {
        for (String key : sessionIds.keySet()) {
            if (!"niezhiliang9595".equals(sessionIds.get(key)))
            {
                sendMessage(msg, sessionIds.get(key));
            }
        }
    }

    /**
     * 多个人发送给指定的几个用户
     * @param msg
     * @param persons  用户s
     */

    public synchronized static void SendMany(String msg,String [] persons) {
        for (String userid : persons) {
            sendMessage(msg, userid);
        }

    }
}
