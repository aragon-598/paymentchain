package com.paymentchain.customer.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.common.CustomerResponseMapper;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.CustomerProduct;
import com.paymentchain.customer.exception.BusinessRuleException;
import com.paymentchain.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Customer API", description = "Customer endpoints")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @Autowired
    CustomerResponseMapper mapper;

    @Value("${spring.datasource.username}")
    private String usernameDB;

    @Operation(description = "Return all customers", summary = "Return 204 if no content data")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="Success")})
    @GetMapping(value="/")
    public ResponseEntity<?> findAllCustomers() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "USERNAME ====>"+usernameDB);
        
        List<Customer> allCustomers =service.getAllCustomers();

        if (!allCustomers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.entityListToDtoList(allCustomers));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mapper.entityListToDtoList(allCustomers));
        }

    }
    
    @Operation(description = "Return customer by ID", summary = "Return 404 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @GetMapping(value="/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable("id") long id) {
        boolean existe = service.existsById(id);

        if (existe) {
            ;
            return ResponseEntity.status(HttpStatus.OK).body(mapper.entityToDto(service.getCustomerById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el customer");
        }
    }

    @Operation(description = "Returns client with all products and transactions by code", summary = "Return 404 if no data found")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful"),@ApiResponse(responseCode="500",description="Internal server error")})
    @GetMapping(value="/bycode")
    public ResponseEntity<?> findCustomerByCode(@RequestParam String code) {
        Customer customer =service.getCustomerByCode(code);

        if (customer!=null) {
            List<CustomerProduct> products = customer.getProducts();
            products.forEach(p -> {
                String productName = service.getProductNameById(p.getProductId());
                p.setProductName(productName);
            });

            List<?> transactions = service.getTransactionsByIban(customer.getIban());
            customer.setTransactions(transactions);

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro al customer por el codigo");
        }
    
    }

    @Operation(description = "Save customer information", summary = "Return 201 if data is good")
    @ApiResponses(value = {@ApiResponse(responseCode="201",description="Succeded")})
    @PostMapping(value="/")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) throws BusinessRuleException {
        
        service.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
    
    @Operation(description = "Update customer information looking for id", summary = "Return 404 if the customer not exists")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) throws BusinessRuleException {
        boolean existe = service.existsById(id);

        if (existe) {
            customer.setCustomerId(id);
            service.saveCustomer(customer);
            return ResponseEntity.status(HttpStatus.OK).body("Updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se actualizo el customer porque no existe");
        }
    }

    @Operation(description = "Delete customer by id", summary = "Return 404 if not exists")
    @ApiResponses(value = {@ApiResponse(responseCode="200",description="successful")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id")long id) {
        boolean existe = service.existsById(id);

        if (existe) {
            service.deleteCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se elimino el usuario porque no existe");
        }
    }
    
}
