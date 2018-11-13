package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic");

//        config.setApplicationDestinationPrefixes("/app");//目标端点的前缀，即客户端的前缀
    }

    /**
     * 设置连接服务器时的端点，即客户端调用connect时的端点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        UrlPathHelper urlPathHelper=new UrlPathHelper();
//        registry.setUrlPathHelper(urlPathHelper);
//        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*");
    }

}