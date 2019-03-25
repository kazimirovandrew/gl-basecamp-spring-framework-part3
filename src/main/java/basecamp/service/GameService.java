package basecamp.service;

import basecamp.domain.NumberEntity;
import basecamp.repository.NumberEntityRepository;
import basecamp.wire.CreateNumberResponse;
import basecamp.wire.PlayGameResponse;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@NoArgsConstructor
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private NumberEntityRepository numberEntityRepository;
    @NonNull
    private NumberEntity numberEntity;

    private final Random random = new Random();

    public CreateNumberResponse createNumber() {
        int number = random.nextInt(101);

        numberEntity.setNumber(number);
        numberEntityRepository.save(numberEntity);

        CreateNumberResponse response = new CreateNumberResponse();
        response.setId(numberEntity.getId());
        response.setNumber(number);

        return response;
    }

    public PlayGameResponse isGameWon(String id, int guess) {

        int number = numberEntityRepository
                .findById(Integer.parseInt(id))
                .get()
                .getNumber();

        String result = (guess == number) ? "Winner!" : "Looser!";

        PlayGameResponse response = new PlayGameResponse();
        response.setResult(result);

        return response;
    }
}
