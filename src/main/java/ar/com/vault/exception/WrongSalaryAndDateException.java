package ar.com.vault.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alejandro on 18/11/18.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Parámetros incorrectos")
public class WrongSalaryAndDateException extends RuntimeException {

    public WrongSalaryAndDateException() {
        super();
    }

    public WrongSalaryAndDateException(String message) {
        super(message);
    }

}
