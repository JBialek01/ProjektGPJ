package pl.games.lotek.web;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import pl.games.lotek.core.*;
import pl.games.lotek.repository.CheckWinEntity;
import pl.games.lotek.repository.LotekTicketEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@RestController
@Scope("request")
@AllArgsConstructor
public class LotekGameController {

    private final LotekGameService lotekGameService;
    private final LotekUserNumberWebProvider userNumbersProvider;

    @GetMapping("/lotekSubmitTicket")
    public ResponseEntity<String> submitTicket(@AuthenticationPrincipal OAuth2User user,
                                               @RequestParam("number1") Integer number1,
                                               @RequestParam("number2") Integer number2,
                                               @RequestParam("number3") Integer number3,
                                               @RequestParam("number4") Integer number4,
                                               @RequestParam("number5") Integer number5,
                                               @RequestParam("number6") Integer number6) {
        userNumbersProvider.addNumbers(Set.of(number1, number2, number3, number4, number5, number6));
        TicketSubmission message = lotekGameService.submitTicket(user);
        return ResponseEntity.ok(message.toString());
    }

    @GetMapping("/checkWin")
    public List<CheckWinEntity> checkWinResults(@AuthenticationPrincipal OAuth2User user) {
        return lotekGameService.checkWin(user);
    }

    @GetMapping("/lotekGameHistory")
    public ResponseEntity<List<LotekTicketEntity>> fetchGameHistoryForAllUsers(){
        return lotekGameService.showAllTickets();
    }
}
