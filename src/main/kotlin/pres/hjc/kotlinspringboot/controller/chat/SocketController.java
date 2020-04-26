package pres.hjc.kotlinspringboot.controller.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pres.hjc.kotlinspringboot.function.socket.SocketService;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/4/26
 * @time 13:41
 */
@RestController
public class SocketController {

    @GetMapping("/push/{userId}/{token}")
    public ResponseEntity<String> pushMsg(String msg,
                                          @PathVariable("userId")String userId ,
                                          @PathVariable("token")String token) throws IOException {
        SocketService.sendInfo(msg, userId, token);
        return ResponseEntity.ok("SUCCESS");
    }
    // sock js


}
