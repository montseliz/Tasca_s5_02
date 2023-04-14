package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Message {

    @Schema(description = "Http Status", example = "201")
    private int statusCode;

    @Schema(description = "Date and time", example = "2023-04-12T21:04:31.274+00:00")
    private Date timestamp;

    @Schema(description = "Description message", example = "Player created and added successfully into the database")
    private String textMessage;

    @Schema(description = "Uri path", example = "uri=/players/add")
    private String description;

}