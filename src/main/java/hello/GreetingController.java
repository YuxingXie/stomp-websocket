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


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        Greeting ret= new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        ObjectMapper mapper = new ObjectMapper();


        String json = mapper.writeValueAsString(ret);
        System.out.println(json);
        return ret;
    }

}
