package starwars;

//definition for case when not found elements.
class CharacterNotFoundException extends RuntimeException {

    CharacterNotFoundException(Long id) {
        super("Could not find Character " + id);
    }
}
