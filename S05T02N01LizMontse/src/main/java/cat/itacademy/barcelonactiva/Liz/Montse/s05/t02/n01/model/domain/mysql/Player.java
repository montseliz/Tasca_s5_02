package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long player_id;

    @Column(name = "name")
    private String name;

    @Column (name = "registration_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime registration;

    /**
     * La propietat "mappedBy" s'estableix en "player" per indicar que la relació @OneToMany està mapejada per l'atribut "player" de l'entitat Game.
     * En el cas de "fetch" en "EAGER" significa que els "games" es carregaran automàticament en la memòria quan el "player" es carregui.
     * Pel que fa a la propietat "orphanRemoval" en "true" vol dir que els "games" s'eliminaran en el moment en què s'elimini un "player".
     * La propietat "targetEntity" fa referència a la classe de l'entitat relacionada. Per tant, la relació @OneToMany apuntarà a la classe Game.
     * Finalment, la propietat "cascade" s'estableix en "ALL" per indicar que totes les operacions de cascada (creació, actualització i eliminació),
     * que s'apliquin a l'entitat Player, també s'aplicaran automàticament a l'entitat Game.
     */
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Game.class, cascade = CascadeType.ALL)
    private Set<Game> gamesHistory = new HashSet<>();

    public Player(String name) {
        this.name = name;
        this.registration = LocalDateTime.now();
    }

}
