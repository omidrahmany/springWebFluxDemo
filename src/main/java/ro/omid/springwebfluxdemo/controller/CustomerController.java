package ro.omid.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ro.omid.springwebfluxdemo.dto.Customer;
import ro.omid.springwebfluxdemo.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("core")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/")
    public List<Customer> getCustomers(){
        return service.loadAllCustomers();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersAsFlux(){
        return service.loadAllCustomersAsFlux();
    }
}
