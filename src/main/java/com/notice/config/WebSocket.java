package com.notice.config;

import com.google.gson.Gson;
import com.notice.vo.ContentVo;
import com.notice.vo.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;
    private String username;
    //存放session的set集合   Set就是不重复的元素
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, String> map = new HashMap<>();
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        //获取用户名
        /**
         *   String queryString = request.getQueryString();
                               上面这句话的意思是：获取查询字符串。比如客户端发送http://localhost/test.do?a=b&c=d&e=f
                                通过request.getQueryString()得到的是  a=b&c=d&e=f   前端页面发送 --------->
             webSocket = new WebSocket('ws://localhost:8080/webSocket?username=' + '${username}');

         */
        String s = session.getQueryString();
        //获取到当前登录的用户名
        String urlUsername = s.split("=")[1];
        try {
        	//指定编码集
            username = URLDecoder.decode(urlUsername, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //把SessionID和用户名放进集合里面
        //map中就是   (sessionid：张三)
        map.put(session.getId(), username);
        System.out.println("有新的连接，总数：" + webSockets.size() + "  sessionId：" + session.getId() + "  " + username);
        String content = username +  "进入了聊天室！";
        Message message = new Message(content, map);
        //将消息转化为json字符串发送
        send(message.toJson());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        map.remove(session.getId());
        System.out.println("有新的断开，总数：" + webSockets.size() + "  sessionId：" + session.getId());
        String content = "\"" + username + "\"  离开了聊天室！";
        Message message = new Message(content, map);
        send(message.toJson());
    }


    private static Gson gson = new Gson();
	/**
	 * 收到信息时触发，客户端发送消息的时候触发
	 *  前端代码：在send()方法里面获取到文本内容，
	 *  webSocketSend(htmlstr,message,"");
	 *  webSocket.send(msg);
	 */
    @OnMessage
    public void onMessage(String json) {
         // fromJson()分别是json字符串以及需要转换对象的类型。
        ContentVo contentVo = gson.fromJson(json, ContentVo.class);

         //页面发送send()方法，携带type属性，以及to，以及mag
        if (contentVo.getType() == 1) {
            //广播
            Message message = new Message();
            //将发送人，以及发送的消息封装在message的content属性中
            message.setContent(this.username, contentVo.getMsg());
            message.setNames(map);
            //转化为json字符串进行发送
            send(message.toJson());
            System.out.println(message.toJson());
        } else {
            //单聊
            Message message = new Message();
            //将发送人，以及发送的消息封装在message的content属性中
            message.setContent(this.username, contentVo.getMsg());
            message.setNames(map);

            String to = contentVo.getTo();
            String tos[] = to.substring(0, to.length() - 1).split("-");
            List<String> lists = Arrays.asList(tos);
            for (WebSocket webSocket : webSockets) {
                if (lists.contains(webSocket.session.getId()) && webSocket.session.getId() != this.session.getId()) {
                    try {
                    	//发送消息
                        webSocket.session.getBasicRemote().sendText(message.toJson());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    /**
     * 
     * @param message  
     */
    public void send(String message) {
        for (WebSocket webSocket : webSockets) {
            try {
            	//websocket  session发送文本消息的方法
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
