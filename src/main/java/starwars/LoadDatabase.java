package starwars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //Some characters for initiation database characters.
    @Bean
    CommandLineRunner initDatabase(CharacterRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Character(
                    "Luke Skywalker", "172",
                    "77", "blond",
                    "fair", "blue",
                    "19BBY", "male",
                    "https://swapi.dev/api/planets/1/",
                    new String[]{"https://swapi.dev/api/films/2/",
                            "https://swapi.dev/api/films/6/",
                            "https://swapi.dev/api/films/3/" +
                                    "https://swapi.dev/api/films/1/" +
                                    "https://swapi.dev/api/films/7/"},
                    new String[]{"https://swapi.dev/api/species/1/"},
                    new String[]{"https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/30/"},
                    new String[]{"https://swapi.dev/api/starships/12/", "https://swapi.dev/api/starships/22/"},
                    "2014-12-09T13:50:51.644000Z",
                    "2014-12-20T21:17:56.891000Z",
                    "https://swapi.dev/api/people/1/")));

//            Character(String name,
//                    String height,
//                    String mass,
//                    String hairColor,
//                    String eyeColor,
//                    String birthYear,
//                    String gender,
//                    String homeworld,
//                    String films[],String species[], String vehicles[], String starships[], String created, String edited,
//                    String url)

            log.info("Preloading " + repository.save(new Character("C-3PO", "167",
                    "75", "n/a",
                    "gold", "yellow",
                    "112BBY", "n/a",
                    "http://swapi.dev/api/planets/1/",
                    new String[]{"https://swapi.dev/api/films/1/",
                            "https://swapi.dev/api/films/2/",
                            "https://swapi.dev/api/films/3/" +
                                    "https://swapi.dev/api/films/4/" +
                                    "https://swapi.dev/api/films/5/" +
                                    "https://swapi.dev/api/films/6/"},
                    new String[]{"https://swapi.dev/api/species/2/"},
                    new String[]{},
                    new String[]{},
                    "2014-12-10T15:10:51.357000Z", "2014-12-20T21:17:50.309000Z",
                    "http://swapi.dev/api/people/2/")));
        };
    }
}