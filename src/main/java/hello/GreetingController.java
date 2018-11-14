package hello;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
@CrossOrigin("*")
public class GreetingController {


    @MessageMapping("/app/hello")//注意WebSocketConfig中配置的前缀"/app"，所以控制器匹配完整路径是/app/hello
//    @SendTo("/topic/greetings")//客户端的端点，这个在前端使用SocketJs定义
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        Greeting ret= new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        ObjectMapper mapper = new ObjectMapper();


        String json = mapper.writeValueAsString(ret);
        System.out.println(json);
        return ret;
    }

}
