package pl.games.lotek.infrastructure.usersHitsRanking.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.games.lotek.domain.usershitsranking.UsersHitsRankingFacade;
import pl.games.lotek.domain.usershitsranking.dto.UsersHitsRankingDto;

import java.util.List;

@RestController
@Scope("request")
@AllArgsConstructor
@RequestMapping("/ranking")
public class UsersHitsRankingController {

    private final UsersHitsRankingFacade usersHitsRankingFacade;

    @PostMapping("/generate")
    public ResponseEntity<List<UsersHitsRankingDto>> generateAndGetRankingForPreviousDay() {
        usersHitsRankingFacade.generateRankingForYesterday();
        List<UsersHitsRankingDto> usersHitsRankingDtos = usersHitsRankingFacade.fetchAllRankingEntries();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }

    @GetMapping
    public ResponseEntity<List<UsersHitsRankingDto>> getRankingForPreviousDay() {
        List<UsersHitsRankingDto> usersHitsRankingDtos = usersHitsRankingFacade.fetchAllRankingEntries();
        return ResponseEntity.ok(usersHitsRankingDtos);
    }

    @GetMapping("/{daysToSubstract}")
    public ResponseEntity<List<UsersHitsRankingDto>> getRankingForSpecifiedDay(@PathVariable("daysToSubstract") Long days) {
        List<UsersHitsRankingDto> usersHitsRankingDtos = usersHitsRankingFacade.fetchAllRankingEntriesByDay(days);
        return ResponseEntity.ok(usersHitsRankingDtos);
    }
}
