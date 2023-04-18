package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.repository.mongodb;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mongodb.PlayerMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerMongoRepository extends MongoRepository<PlayerMongo, ObjectId> {

    boolean existsByName(String name);

    boolean existsById(ObjectId id);

}
