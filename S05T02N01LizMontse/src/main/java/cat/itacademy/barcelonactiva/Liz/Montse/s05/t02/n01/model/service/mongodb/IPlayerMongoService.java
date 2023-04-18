package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mongodb.PlayerMongo;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb.PlayerMongoDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface IPlayerMongoService {

    PlayerMongo getPlayerById(ObjectId id);
    List<PlayerMongo> getListPlayers();
    PlayerMongoDTO createPlayer(PlayerMongoDTO playerDTO);
    PlayerMongoDTO editPlayer(ObjectId id, PlayerMongoDTO playerDTO);
    List<PlayerMongoDTO> getPlayersWithWinPercentage();
    String getWinningAverage();
    PlayerMongoDTO getMostLoser();
    PlayerMongoDTO getMostWinner();

}