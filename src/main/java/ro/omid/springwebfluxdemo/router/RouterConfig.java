package ro.omid.springwebfluxdemo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ro.omid.springwebfluxdemo.handler.CustomerHandler;
import ro.omid.springwebfluxdemo.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/stream/customers", streamHandler::loadCustomersAsStream)
                .GET("/router/customers/{customer-id}", handler::findCustomerById)
                .POST("/routers/save", handler::saveCustomer)
                .build();
    }
}
