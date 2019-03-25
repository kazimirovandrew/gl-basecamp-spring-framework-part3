package basecamp.controller;

import basecamp.exception.InvalidNumberRangeException;
import basecamp.service.GameService;
import basecamp.wire.CreateNumberResponse;
import basecamp.wire.PlayGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("game")
@Validated
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //http://localhost:8080/guess_number/game/create
    @PostMapping("/create")
    public CreateNumberResponse create() {
        CreateNumberResponse response = gameService.createNumber();
        return response;
    }

    //http://localhost:8080/guess_number/game/play/{your_id}/{your_number}
    @GetMapping("/play/{id}/{num}")
    public PlayGameResponse play(
            @PathVariable @NotBlank(message = "Id must not be blank!") String id,
            @PathVariable int num) {

        if (num >= 0 && num <= 100) {

            PlayGameResponse response = gameService.isGameWon(id, num);
            return response;

        } else {
            throw new InvalidNumberRangeException("Invalid number range! Range = [0:100]");
        }
    }
}
