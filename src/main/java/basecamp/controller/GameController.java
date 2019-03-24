package basecamp.controller;

import basecamp.domain.NumberEntity;
import basecamp.exception.InvalidNumberRangeException;
import basecamp.service.GameService;
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

    @PostMapping("/create")
    public NumberEntity create() {
        NumberEntity numberEntity = gameService.createNumber();
        return numberEntity;
    }

    @GetMapping("/play/{id}/{num}")
    public GameService play(
            @PathVariable @NotBlank(message = "Id must not be blank!") String id,
            @PathVariable int num) {


        if (num >= 0 && num <= 100) {

            gameService.isGameWon(id, num);
            return gameService;

        } else {
            throw new InvalidNumberRangeException("Invalid number range! Range = [0:100]");
        }
    }
}
