package com.autotrading.quotes;

import com.alibaba.fastjson.JSONObject;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author : YC
 * @class  :
 * @email  : 37933599@qq.com
 * @createDate :2018/5/15 下午12:09
 * @modifyDate :2018/5/15
 * @annocation :webSoket 链接
 */
public class WebSocketCli {
    public static WebSocketClient client;
    public static void main(String[] args) {
        try {
            client = new WebSocketClient(new URI("wss://api.huobipro.com/ws"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("打开链接");
                }

                @Override
                public void onMessage(String s) {
                    System.out.println("收到消息"+s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("链接已关闭");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    System.out.println("发生错误已关闭");
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        client.connect();

        while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
            System.out.println("正在连接服务器...");
        }
        System.out.println("连接到服务器");
        try {
            JSONObject obj = new JSONObject();
            obj.put("png", "18212558000");
            String query = obj.toString();
            send(query.getBytes("utf-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //client.send("hello world");
      //  client.close();
    }


    public static void send(byte[] bytes){
        client.send(bytes);
    }

}
