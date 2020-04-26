package pres.hjc.kotlinspringboot.function.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/4/26
 * @time 12:43
 */
@ServerEndpoint("/mychat/{userId}/{token}")
@Slf4j
@Component
public class SocketService {

    /**
     * 当前在线人数
     */
    private static int onLineCount = 0;

    /**
     * 存放web socket对象
     * 线程安全的
     */
    private static ConcurrentHashMap<String, SocketService> hashMap = new ConcurrentHashMap<>();

    /**
     * 连接会话，发送数据
     */
    private Session session;

    /**
     * userId
     */
    private String userId;
    /**
     * token
     */
    private String token;

    /**
     * 连接成功
     * @param session
     * @param userId
     * @param token
     */
    @OnOpen
    public void open (Session session , @PathParam("userId") String userId, @PathParam("token") String token){
        this.session = session;
        this.userId = userId;
        this.token = token;

        if (hashMap.containsKey(userId)){
            hashMap.remove(userId);
            //push
            hashMap.put(userId, this);

        }else {
            hashMap.put(userId,this);
            //上线 +1
            setOnLineCount();
        }
        log.info("user link" + userId + " hav ->" + getOnLineCount());

        //处理下面抛出来的异常
        try {
            sendMsg("success");
        } catch (IOException e) {
            log.error("user" + userId + " -> return .");
        }
    }


    /**
     * 关闭连接回调
     */
    @OnClose
    public void close(){
        if (hashMap.containsKey(userId)){
            hashMap.remove(userId);
            // -
            subOnLineCount();
        }
        log.info("user" + userId + " return -> hav " + getOnLineCount());
    }

    /**
     * 收到客户端的消息
     * @param msg
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMsg(String msg,  Session session) throws IOException {
        log.info( " user" + userId + " sendMgs ->" + msg);
        //群发，或者存到数据库
        if (!StringUtils.isEmpty(msg)){
            //解析发送的msg
            JSONObject jsonObject = JSON.parseObject(msg);
            //追加发送人，防止串改
            jsonObject.put("fUser" , this.userId);
            String toUser = jsonObject.getString("toUser");
            //send
            if (!StringUtils.isEmpty(toUser) && hashMap.containsKey(toUser)){
                hashMap.get(toUser).sendMsg(jsonObject.toJSONString());
            }else {
                log.error(" null  -> userId");
                //send mysql or redis
            }
        }
    }

    /**
     * 产生error
     * @param session
     * @param throwable
     */
    @OnError
    public void error(Session session , Throwable throwable){
        log.error("ID" + userId + " error , msg = " + throwable.getMessage());
        throwable.printStackTrace();
    }

    /**
     * 服务器主动推送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    /**
     * 自定义推送
     * @param msg
     * @param userId
     * @param token
     */
    public static void sendInfo(String msg,
                                @PathParam("userId")String userId ,
                                @PathParam("token")String token) throws IOException {

        log.info("推送消息到 ->" + userId + " ; msg = " + msg + " : token ->" + token);

        if (!StringUtils.isEmpty(userId) && hashMap.containsKey(userId)){
            hashMap.get(userId).sendMsg(msg);
        }else {
            log.warn("user" + userId + " is not online.");
        }
    }


    /**
     * 保证线程安全
     * @return
     */
    public static synchronized int getOnLineCount(){
        return onLineCount;
    }

    public static synchronized void setOnLineCount(){
        SocketService.onLineCount ++;
    }

    public static synchronized void subOnLineCount(){
        SocketService.onLineCount --;
    }
}
