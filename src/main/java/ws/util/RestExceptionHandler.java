/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AccessDeniedException.class })
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        String errorMessage = "No cuenta con los roles necesarios para acceder a este recurso.";
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}