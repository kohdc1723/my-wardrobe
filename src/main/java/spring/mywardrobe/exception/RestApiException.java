package spring.mywardrobe.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import spring.mywardrobe.exception.errorCode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
    private final ErrorCode errorCode;
}
