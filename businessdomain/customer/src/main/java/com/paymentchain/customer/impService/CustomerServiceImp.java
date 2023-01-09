package com.paymentchain.customer.impService;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import com.paymentchain.customer.service.CustomerService;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private WebClient.Builder wBuilder;

    //web client requires http client library to work propertly
    HttpClient  client = HttpClient.create()
                                    // connection timeout: is a period within which a connection between client and server must be established
                                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 50000)
                                    .option(ChannelOption.SO_KEEPALIVE, true)
                                    .option(EpollChannelOption.TCP_KEEPIDLE, 300)
                                    .option(EpollChannelOption.TCP_KEEPINTVL, 60)
                                    // response timeout: the maximum time we wait to receive a response after sending a request
                                    .responseTimeout(Duration.ofSeconds(1))
                                    //read and write timeout: a read timeout occurs when no data was read within a certain
                                    //period of time, while the write timeout when a write operation cannot finish a specific time
                                    .doOnConnected(connection -> {
                                        connection.addHandlerLast(new ReadTimeoutHandler(5000,TimeUnit.MILLISECONDS));
                                        connection.addHandlerLast(new WriteTimeoutHandler(5000,TimeUnit.MILLISECONDS));
                                    });

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        repository.deleteById(id);
    }

    @Override
    public String getProductNameById(long idProduct) {
        WebClient build = wBuilder.clientConnector(new ReactorClientHttpConnector(client))
                                    .baseUrl("http://localhost:8082/product")
                                    .defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
                                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8082/product"))
                                    .build();
        JsonNode block = build.method(HttpMethod.GET).uri("/"+idProduct)
                                .retrieve().bodyToMono(JsonNode.class)
                                .block();
        String name = block.get("name").asText();
        return name;
    }
    
}
