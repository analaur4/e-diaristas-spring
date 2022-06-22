package br.com.adminediaristas.api.handlers;

import br.com.adminediaristas.api.dtos.responses.ErrorResponse;
import br.com.adminediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EnderecoServiceException.class)
    public ResponseEntity<ErrorResponse> handleEnderecoServiceException(EnderecoServiceException exception, HttpServletRequest request) {
        var errorResponse = ErrorResponse.builder()
                .status(400)
                .timestamp(LocalDateTime.now())
                .message(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
