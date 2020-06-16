package starwars;

class CharacterNotFoundException extends RuntimeException {

    CharacterNotFoundException(Long id) {
        super("Could not find Character " + id);
    }
}
