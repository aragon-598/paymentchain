# PaymentChain
## Microservices Spring boot project

Proyecto de java spring boot para el manejo de microservicios, gateways, eureka server, keycloak etc.
## Instrucciones

Consta de 8 proyectos spring boot

- Infraestructure domain: eurekaServer, configServer, apiGateway, adminServer y keycloakAdapter
- Business domain: Product, Transaction y Customer

Levantamos keycloak y DB

```sh
cd keycloak/ && sudo docker compose up -d
```

Levantamos los servicios en el siguiente orden:

- Eureka server
- ConfigServer
- ApiGateWay
- AdminServer
- KeyCloak Adapter
- Customer service
- Product service
- Transaction service

```sh 
cd infraestructure/eurekaserver && mvn spring-boot:run
cd infraestructure/configserver && mvn spring-boot:run
cd infraestructure/apigateway && mvn spring-boot:run
cd infraestructure/adminserver && mvn spring-boot:run
cd infraestructure/keycloak-adapter && mvn spring-boot:run
cd infraestructure/customer && mvn spring-boot:run
cd infraestructure/product && mvn spring-boot:run
cd infraestructure/transaction && mvn spring-boot:run
```

O en su defecto levantamos todos los servicios mediante docker compose y como requerimiento generamos los jar, en dado caso la maquina soporte los procesos, crear un dockerfile por servicio que genere los jar por si solo

```sh 
sudo docker compose up -d
```

## Ve a postman y consulta las url

```sh 
http://localhost:8000/customer/
http://localhost:8000/product/
http://localhost:8000/transaction/
```

Gracias al apigateway podemos acceder a todos los servicios mediante el mismo dominio

## License

MIT

**Free Software!**
