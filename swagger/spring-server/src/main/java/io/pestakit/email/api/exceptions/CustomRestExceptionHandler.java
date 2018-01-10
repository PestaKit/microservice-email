package io.pestakit.email.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class will handle exceptions
 * We use this to send more specific messages when an exception occurs.
 * @author Tano Iannetta
 */
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String NO_MAIL_SENT = "No mail was sent";
    public static final String ERROR_OCCURRED = "An error occurred";

    /**
     * Handle badRequestException
     * @param ex exception
     * @param headers header to send
     * @param status to send
     * @param request that caused    the error
     * @return handle that send back the response
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }


    // handle exceptions
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        // handle MailParseException
        if (ex.getClass() == MailParseException.class) {
            ApiError apiError = new ApiError(
                    HttpStatus.UNPROCESSABLE_ENTITY, NO_MAIL_SENT ,ex.getLocalizedMessage());
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        } else if (ex.getClass() == AddressException.class) {
            ApiError apiError = new ApiError(
                    HttpStatus.UNPROCESSABLE_ENTITY, NO_MAIL_SENT ,ex.getLocalizedMessage());
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }
        else{ // all other exceptions
            ApiError apiError = new ApiError(
                    HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ERROR_OCCURRED);
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }

    }

}
