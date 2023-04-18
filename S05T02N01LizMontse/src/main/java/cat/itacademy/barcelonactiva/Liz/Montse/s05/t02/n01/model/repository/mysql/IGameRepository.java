package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.repository.mysql;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {

    default boolean isEmpty() {
        return count() == 0;
    }
}

