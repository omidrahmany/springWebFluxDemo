package ro.omid.springwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class MonoFluxTest {

    @Test
    public void testMonoFlux() {
        Mono<?> publisher = Mono.just("hello world!")
                .then(Mono.error(new RuntimeException("something went wrong!")))
                .log();
        Consumer<Object> subscriber = System.out::println;
        publisher.subscribe(subscriber, error -> System.out.println(error.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<?> fluxPublisher = Flux.just("spring", "hibernate", "reactive", "web flux")
                .concatWithValues("Docker")
                .concatWith(Flux.error(new RuntimeException("something went wrong in the flux!")))
                .concatWithValues("Cloud")
                .log();
        Consumer<Object> subscriber = System.out::println;
        fluxPublisher.subscribe(subscriber, error -> System.out.println(error.getMessage()));
    }

}
