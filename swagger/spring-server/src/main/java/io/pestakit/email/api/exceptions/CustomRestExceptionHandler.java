package io.pestakit.email.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * This class will handle exceptions
 * We use this to send more specific messages when en exception occurs.
 *
 * @author Tano Iannetta
 */
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    // handle all exceptions
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        // handle MailParseException
        if (ex.getClass() == MailParseException.class) {
            ApiError apiError = new ApiError(
                    HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(), "No mail was sent");
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        } else { // all other exceptions
            ApiError apiError = new ApiError(
                    HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occured");
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }

    }

}
