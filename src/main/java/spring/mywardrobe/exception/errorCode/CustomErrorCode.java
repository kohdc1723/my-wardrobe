package spring.mywardrobe.exception.errorCode;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {
    EMAIL_DOES_NOT_EXIST(HttpStatus.UNAUTHORIZED, "Email does not exist"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "Wrong password"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "Email already in use"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found"),
    REQUEST_PARAMETER_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "Request parameter type mismatch"),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "Invalid request body"),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid request parameter"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Parameter is invalid"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final HttpStatus httpStatus;
    private final String message;
}
