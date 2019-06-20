package io.demo.jony.jony.core.config;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.demo.jony.jony.core.dto.RestMessageDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.exception.RestException;

/**
 * Handler for REST Exceptions.
 *
 * @author Virtus
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final String UNEXPECTED_ERROR = "exception.unexpected";
    private static final Logger LOGGER = Logger.getLogger(RestExceptionHandler.class.getName());
    private final MessageSource messageSource;

    /**
     * Constructor.
     *
     * @param messageSource Message Source.
     */
    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    /**
     * Handler for: Business Exception.
     *
     * @param ex     Exception.
     * @param locale Locale.
     * @return Message response.
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<RestMessageDTO> handleBusinessExpecption(BusinessException ex) {
        return new ResponseEntity<>(new RestMessageDTO(ex.getMessage(), ex.getCode()), HttpStatus.CONFLICT);
    }

    /**
     * Handler for: Illegal Argument.
     *
     * @param ex     Exception.
     * @param locale Locale.
     * @return Message response.
     */
    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<RestMessageDTO> handleIllegalArgument(RestException ex) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), LocaleContextHolder.getLocale());
        return new ResponseEntity<>(new RestMessageDTO(errorMessage), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for: Argument Not Valid.
     *
     * @param ex     Exception.
     * @param locale Locale.
     * @return Message response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestMessageDTO> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new RestMessageDTO(errorMessages), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for: All Exceptions.
     *
     * @param ex     Exception.
     * @param locale Locale.
     * @return Message response.
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestMessageDTO> handleExceptions(Exception ex) {
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, LocaleContextHolder.getLocale());
        LOGGER.log(Level.ALL, ex.toString(), ex);
        return new ResponseEntity<>(new RestMessageDTO(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}