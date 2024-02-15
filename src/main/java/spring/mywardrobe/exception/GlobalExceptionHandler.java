package spring.mywardrobe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import spring.mywardrobe.exception.errorCode.CustomErrorCode;
import spring.mywardrobe.exception.errorCode.ErrorCode;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e) {
        ErrorCode errorCode = e.getErrorCode();

        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.warn("EmptyResultDataAccessException: ", e);

        ErrorCode errorCode = CustomErrorCode.RESOURCE_NOT_FOUND;

        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException e) {
        log.warn("NoResourceFoundException: ", e);

        ErrorCode errorCode = CustomErrorCode.RESOURCE_NOT_FOUND;

        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException: ", e);

        ErrorCode errorCode = CustomErrorCode.INVALID_PARAMETER;

        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("MethodArgumentTypeMismatchException: ", e);

        ErrorCode errorCode = CustomErrorCode.REQUEST_PARAMETER_TYPE_MISMATCH;

        return handleExceptionInternal(errorCode, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("MethodArgumentNotValidException: ", e);

        ErrorCode errorCode = CustomErrorCode.INVALID_REQUEST_BODY;

        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList().get(0);

        return handleExceptionInternal(errorCode, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e) {
        log.warn("Exception: ", e);

        ErrorCode errorCode = CustomErrorCode.INTERNAL_SERVER_ERROR;

        return handleExceptionInternal(errorCode);
    }

    // overload handleExceptionInternal
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(buildErrorResponse(errorCode));
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(buildErrorResponse(errorCode, message));
    }

    // overload buildErrorResponse
    private ErrorResponse buildErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    private ErrorResponse buildErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }
}
