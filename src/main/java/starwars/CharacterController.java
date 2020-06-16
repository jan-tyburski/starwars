package starwars;

import java.util.List;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Need to disable security feature in properties file.
@EnableSpringBootMetricsCollector
@RestController
public
class CharacterController {

    private final CharacterRepository repository;
    static final Counter requests = Counter.build()
            .name("requests_total").help("Total number of requests.").register();
    // Define a histogram metric for /prometheus
    static final Histogram requestLatency = Histogram.build()
            .name("requests_latency_seconds").help("Request latency in seconds.").register();

    CharacterController(CharacterRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/characters")
    List<Character> all() {

        Histogram.Timer requestTimer = requestLatency.startTimer();
        try {
        return repository.findAll();
        } finally {
            // Stop the histogram timer
            requestTimer.observeDuration();
        }
    }

    @PostMapping("/characters")
    Character newCharacter(@RequestBody Character newCharacter) {
        return repository.save(newCharacter);
    }

    // Single item

    @GetMapping("/characters/{id}")
    Character one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @PutMapping("/characters/{id}")
    Character replaceCharacter(@RequestBody Character newCharacter, @PathVariable Long id) {

        return repository.findById(id)
                .map(Character -> {
                    Character.setName(newCharacter.getName());
                    Character.setHeight(newCharacter.getHeight());
                    Character.setMass(newCharacter.getMass());
                    Character.setHairColor(newCharacter.getHairColor());
                    Character.setSkin_color(newCharacter.getSkin_color());
                    Character.setEyeColor(newCharacter.getEyeColor());
                    Character.setBirth_year(newCharacter.getBirthYear());
                    Character.setGender(newCharacter.getGender());
                    Character.setHomeworld(newCharacter.getHomeworld());
                    Character.setFilms(newCharacter.getFilms());
                    Character.setSpecies(newCharacter.getSpecies());
                    Character.setVehicles(newCharacter.getVehicles());
                    Character.setStarships(newCharacter.getStarships());
                    Character.setCreated(newCharacter.getCreated());
                    Character.setEdited(newCharacter.getEdited());
                    Character.setUrl(newCharacter.getUrl());
                    return repository.save(Character);
                })
                .orElseGet(() -> {
                    newCharacter.setId(id);
                    return repository.save(newCharacter);
                });
    }

    @DeleteMapping("/characters/{id}")
    void deleteCharacter(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
