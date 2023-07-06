package com.paymentchain.apigateway.setups;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Config>{

    private final WebClient.Builder wBuilder;

    public AuthenticationFilter(WebClient.Builder wBuilder) {
        super(Config.class);
        this.wBuilder=wBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange,chain)->{
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"");
            }
            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");
            // log.info("TOKEN ==================>",parts);
            System.out.println("ENTREEEEEEEEEEEEEE");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {

                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad Authorization structure");
            }   
            return  wBuilder.build()
                    .get()
                    .uri("http://keycloack/roles").header(HttpHeaders.AUTHORIZATION, parts[1])     //http://jeycloak/roles por eureka ponemos el dominios
                    .retrieve()
                        .bodyToMono(JsonNode.class)
                        .map(response -> {  
                            if(response != null){
                            log.info("See Objects: " + response);  
                                //check for Partners rol                                 
                            if(response.get("Partners") == null || StringUtils.isEmpty(response.get("Partners").asText())){
                                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Role Partners missing");
                            }
                            }else{
                                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Roles missing");
                            }
                        return exchange;
                })
                .onErrorMap(error -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Communication Error", error.getCause());})
                .flatMap(chain::filter); 
        }, 1);
    }
    
}
