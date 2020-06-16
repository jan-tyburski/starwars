package starwars;

import io.prometheus.client.Counter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class StarWarsApplication {

    final Counter myCounter = Counter.build()
            .name("Test")
            .help("Total requests recorded.")
            .labelNames("status")
            .register();

    @RequestMapping("/prometheus")
    String prometheus() {

        myCounter.labels("PrometheusLabel").inc();
        return "Prometheus !";
    }

    //app start
    public static void main(String... args) {
        SpringApplication.run(StarWarsApplication.class, args);
    }
}
