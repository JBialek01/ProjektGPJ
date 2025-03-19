package pl.games.lotek.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;
import java.util.UUID;

@Document(collection = "ranking")
@Getter
public class UserHitsRankingEntity {

    @Id
    private String id;
    private Instant date;
    private Integer hits;
    private String userId;

    public UserHitsRankingEntity() {
    }

    public UserHitsRankingEntity(LocalDateTime date, Integer hits, String userId) {
        this.id = UUID.randomUUID().toString();
        this.date = date.toInstant(ZoneOffset.UTC);
        this.hits = hits;
        this.userId = userId;
    }

//    public UserHitsRankingEntity(String id, LocalDateTime date, Integer hits, String userId) {
//        this.id = id;
//        this.date = date;
//        this.hits = hits;
//        this.userId = userId;
//    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.ofInstant(date, ZoneId.systemDefault());
    }
}
