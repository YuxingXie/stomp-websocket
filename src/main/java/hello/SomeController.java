package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

@RestController
public class SomeController {

    @Resource
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板
    @RequestMapping("/call_ws")
    public String callWs(String name) throws Exception {
        System.out.println("call ws,name="+name);
        Thread.sleep(1000);
        simpMessageSendingOperations.convertAndSend("/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(name) + "!"));
        return "calling websocket";
    }

}
