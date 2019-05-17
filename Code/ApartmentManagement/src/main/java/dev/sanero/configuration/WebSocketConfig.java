/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.configuration
 * File name: WebSocketConfig.java
 * Author: Sanero.
 * Created date: May 15, 2019
 * Created time: 8:27:24 PM
 */

package dev.sanero.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
 
/*
 * @author Sanero.
 * Created date: May 15, 2019
 * Created time: 8:27:24 PM
 * Description: TODO - 
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
 
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic");
    config.setApplicationDestinationPrefixes("/apt");
  }
 
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry
    .addEndpoint("/apt-stomp-endpoint")
    .setAllowedOrigins("http://localhost:4200", "http://localhost",
        "http://apartment.test", "http://apartment.com")
    .withSockJS();
  }
}