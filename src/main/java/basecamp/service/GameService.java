package basecamp.service;

import basecamp.domain.NumberEntity;
import basecamp.repository.NumberEntityRepository;
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

    @Getter
    private String result;
    private final Random random = new Random();

    public NumberEntity createNumber() {
        int number = random.nextInt(101);

        numberEntity.setNumber(number);
        numberEntityRepository.save(numberEntity);

        return numberEntity;
    }

    public void isGameWon(String id, int guess) {

        int number = numberEntityRepository
                .findById(Integer.parseInt(id))
                .get()
                .getNumber();

        result = (guess == number) ? "Winner!" : "Looser!";
    }
}
