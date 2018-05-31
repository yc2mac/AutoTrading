package com.autotrading.quotes;/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

import com.alibaba.fastjson.JSONObject;
import com.autotrading.base.util.DateUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;

/** This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded. */
public class WSClient extends WebSocketClient {

	public WSClient(URI serverUri , Draft draft ) {
		super( serverUri, draft );
	}

	public WSClient(URI serverURI ) {
		super( serverURI );
	}

	public WSClient(URI serverUri, Map<String, String> httpHeaders ) {
		super(serverUri, httpHeaders);
	}

	long t1,t2;
	@Override
	public void onOpen( ServerHandshake handshakedata ) {
		System.out.println( "opened connection" );

		//send(query);
		/*try {
			send(query.getBytes("UTF-8"));
			send(query);
			sendPing();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		// if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
	}

	@Override
	public void sendPing() throws NotYetConnectedException {
		super.sendPing();
		System.out.println("ping...");
	}

	@Override
	public void onMessage(ByteBuffer bytes) {
		super.onMessage(bytes);
		System.out.println(bytes);
	/*	Charset charset = null;

		CharsetDecoder decoder = null;

		CharBuffer charBuffer = null;

		try {

			charset = Charset.forName("UTF-8");

			decoder = charset.newDecoder();

			//用这个的话，只能输出来一次结果，第二次显示为空

         //  charBuffer = decoder.decode(bytes);

		 	charBuffer = decoder.decode(bytes.asReadOnlyBuffer());

			System.out.println(charBuffer.toString());

		} catch (Exception ex) {

			ex.printStackTrace();

		}*/
	}

	@Override
	public void onMessage( String message ) {

		System.out.println( "received: " + message );
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		try {
			t2=DateUtil.getTimestampNow().getTime();
			System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason +"time"+(t2-t1)/1000);


		} catch (Exception e) {
			e.printStackTrace();
		}
		// The codecodes are documented in class org.java_websocket.framing.CloseFrame
			}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}

	public static void main( String[] args ) throws URISyntaxException {
	//	WSClient c = new WSClient( new URI( "wss://api.huobipro.com/ws" )); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
	//	WSClient c = new WSClient( new URI( "wss://stream.binance.com:9443/ws/bnbbtc@aggTrade" )); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
		WSClient c = new WSClient( new URI( "wss://47.90.109.236:10441/websocket")); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
	   try {
		   c.connect();
		   while(!c.getReadyState().equals(READYSTATE.OPEN)){

				   System.out.println(c.getReadyState());

		   }
  		   System.out.println("连接服务器成功！");
		   c.sendPing();
		   JSONObject obj = new JSONObject();
		   obj.put("event",  "addChannel");
		   obj.put("channel",  "ok_sub_spot_eos_usdt_ticker");
		   obj.put("binary","0");
		   String query = obj.toString();
		   c.send(query);
	   }catch (Exception e){
	   	e.printStackTrace();
	   }





	}

}