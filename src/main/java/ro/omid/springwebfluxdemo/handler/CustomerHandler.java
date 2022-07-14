package ro.omid.springwebfluxdemo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ro.omid.springwebfluxdemo.dao.CustomerDao;
import ro.omid.springwebfluxdemo.dto.Customer;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {

        return ServerResponse.ok().body(dao.getCustomerList(), Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest request) {

        Integer customerId = Integer.valueOf(request.pathVariable("customer-id"));
        Mono<Customer> customerMono = dao.getCustomerList()
                .filter(customer -> customer.getId() == customerId)
//                .take(1).single();
                .next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> stringMono = customerMono.map(dto -> dto.getId() + ":" + dto.getName() + "\n");
        return ServerResponse.ok().body(stringMono, String.class);

    }


}
