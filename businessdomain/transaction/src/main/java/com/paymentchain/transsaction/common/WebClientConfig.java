package com.paymentchain.transsaction.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    public WebClient.Builder loadBalanceWebClientBuilder(){
        return WebClient.builder();
    }
}
