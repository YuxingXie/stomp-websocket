###STOMP WEBSOCKET DEMO
websocket demo from spring site

代码源自spring官网，加入服务端主动推送

* SimpMessagingTemplate：消息发送模板，从服务端主动推送
* WebSocketConfig:配置端点,双全工
* 客户端试用jQuery框架，如果试用angular等框架，可使用相应angular组件

根据spring版本不同WebSocketConfig可能：

WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer

或者：

WebSocketConfig implements WebSocketMessageBrokerConfigurer 

WebSocket与普通http请求不同的是它需要保持双向通话，所以服务器需要端点，客户端也需要端点。

GreetingController.java:
```java
    @MessageMapping("/hello")//注意WebSocketConfig中配置的前缀"/app"，所以控制器匹配完整路径是/app/hello
    @SendTo("/topic/greetings")//客户端的端点，这个在前端使用SocketJs定义
```
@MessageMapping定义服务器端点，@SendTo发送到客户端端点。

注解只能使用元数据，不可使用变量，如果客户端端点是一个可变值，就需要org.springframework.messaging.simp.SimpMessagingTemplate
主动发送消息：

```java
@RestController
@CrossOrigin("*")
public class SomeController {

    @Resource
    private SimpMessagingTemplate simpMessageSendingOperations;//消息发送模板
    @RequestMapping("/call_ws")
    public Greeting callWs(String name) throws Exception {
        System.out.println("call ws,name="+name);
        Thread.sleep(1000);
        simpMessageSendingOperations.convertAndSend("/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(name) + "!"));
        return new Greeting(name+" is calling websocket");
    }
}
```
在convertAndSend方法中的第一个参数为客户端端点。另外，上面的示例代码也是一个从服务器主动推送消息到客户端的例子。