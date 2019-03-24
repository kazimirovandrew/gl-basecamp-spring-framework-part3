package basecamp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidNumberRangeException extends RuntimeException {

    public InvalidNumberRangeException(String message) {
        super(message);
    }
}
