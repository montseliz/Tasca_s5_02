package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb.GameMongoDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface IGameMongoService {

    GameMongoDTO createGame(ObjectId id);
    void removeGamesByPlayer(ObjectId id);
    List<GameMongoDTO> getGamesHistoryByPlayer(ObjectId id);

}