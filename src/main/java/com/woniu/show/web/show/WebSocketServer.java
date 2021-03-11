package com.woniu.show.web.show;


import com.woniu.show.dao.DanmuCrud;
import com.woniu.show.pojo.Danmu;
import com.woniu.show.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocketServer {
//    @Autowired
//    private DanmuService danmuService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if (session != null) {
            synchronized (session) {
//                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    //给指定用户发送信息
    public void sendInfo(String userName, String message) {
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName) {

        sessionPools.put(userName, session);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + onlineNum);
//        try {
//            sendMessage(session, "欢迎" + userName + "加入连接！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName) {
        sessionPools.remove(userName);
        subOnlineCount();
//        System.out.println(userName + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException {
//        message = "客户端：" + message + ",已收到";
        message = message.replace("sb","");
        message = message.replace("操你妈","");
        message = message.replace("卧槽","");
        message = message.replace("你妈逼","");
        message = message.replace("逼","");
        message = message.replace("操你妈","");
        message = message.replace("日你妈","");
        message = message.replace("爸爸","");
        message = message.replace("恶心","");
        message = message.replace("骂人","");
        message = message.replace("wc","");
        message = message.replace("你好骚","");
        System.out.println(message);

        for (Session session : sessionPools.values()) {
            try {
//                Danmu danmu = new Danmu();
//                danmu.setId(0);
//                danmu.setText(message);
//                danmuService.saveDanmu(danmu);
                sendMessage(session, message);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount() {
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
}