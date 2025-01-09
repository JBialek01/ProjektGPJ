package web;

import app.guessnumber.GuessNumber;
import app.guessnumber.WinChecker;
import app.guessnumber.WinningNumberProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloworldController {


    @GetMapping("/hello")
    public String helloUserByName(@RequestParam("number") int number) {
        GuessNumber guessNumber = new GuessNumber(new WinningNumberProvider(), new WinChecker(
                new UserNumberWebProvider(number)
        ));
        return guessNumber.startGame();
    }
}
