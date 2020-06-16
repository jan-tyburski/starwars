package starwars;

import org.springframework.data.jpa.repository.JpaRepository;

interface CharacterRepository extends JpaRepository<Character, Long> {

}
