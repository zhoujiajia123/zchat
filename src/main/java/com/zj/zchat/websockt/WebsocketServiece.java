package com.zj.zchat.websockt;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


@ServerEndpoint("/ws")
@Service
public class WebsocketServiece {
    private static Map<String,Session> onlineSessions = new HashMap<>();
    @OnOpen
    public void onOpen(Session session){
        String name = session.getUserPrincipal().getName();
        onlineSessions.put(name,session);
        System.out.println(onlineSessions.size());
    }
    @OnClose
    public void onClose(Session session){
        //System.out.println(session.getUserPrincipal().getName());
    }
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println(message);
        String toUser=message.split(":")[0];
        String toMessage=session.getUserPrincipal().getName()+":"+message.split(":")[1];
        for (String string:onlineSessions.keySet()) {
            if(string.equals(toUser)){
                try {
                    sendMessageToOne(onlineSessions.get(string),toMessage);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
    }

    public void sendMessageToOne(Session session,String message)throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void sendMessageToAll(){

    }
}
