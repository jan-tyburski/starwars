package starwars;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

//Controller for character actions.
public class CharacterController {
    private final CharacterRepository repository;

    CharacterController(CharacterRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/characters")
    List<Character> all() {

        return repository.findAll();
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
