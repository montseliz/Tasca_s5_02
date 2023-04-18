package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mongodb;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "players")
public class PlayerMongo {

    @Id
    @Schema(description = "Identifier of the player", example = "643d909f15da8348ee4805c1")
    private ObjectId id;

    @Field(name = "name")
    @Schema(description = "Username of the player", example = "Montse")
    private String name;

    @CreationTimestamp
    @Field(name = "registration_date")
    @Schema(description = "Registration date of the player", example = "2023-04-10 18:46:38.227499")
    private LocalDateTime registration;

    @Field(name = "games")
    @Schema(description = "Player's games history")
    private List<GameMongo> gamesHistory = new ArrayList<>();

    public PlayerMongo(String name) {
        this.name = name;
        this.registration = LocalDateTime.now();
    }

    public void addGamesHistory(GameMongo game) {
        gamesHistory.add(game);
    }

}

