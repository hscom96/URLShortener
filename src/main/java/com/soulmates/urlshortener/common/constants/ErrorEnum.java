package com.soulmates.urlshortener.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /* URL 단축 오류 메세지 정의 */
    URL_SHORTEN_NOT_FOUND(HttpStatus.NOT_FOUND, "USH_000", "해당 단축 URL에 매칭되는 URL이 존재하지 않습니다."),
    URL_FORMAT_ERROR(HttpStatus.NOT_FOUND, "USH_001", "해당 URL은 적절치 않은 형식입니다."),
    /* URL 단축 오류 메세지 정의 끝 */

    /* 기타 오류 메세지 정의 */
    ETC(HttpStatus.INTERNAL_SERVER_ERROR, "ETC_000", "알 수 없는 오류입니다.");
    /* 기타 오류 메세지 정의 끝*/
    
    private final ErrorResponse errorResponse;

    public String getMessage() {
        return this.errorResponse.getMessage();
    }

    public String getErrCode() {
        return this.errorResponse.getErrCode();
    }

    public HttpStatus getHttpStatus() {
        return this.errorResponse.getHttpStatus();
    }

    ErrorEnum(HttpStatus httpStatus, String errCode, String message) {
        this.errorResponse = new ErrorResponse(httpStatus, errCode, message);
    }

    @Getter
    public static class ErrorResponse {
        private final HttpStatus httpStatus;
        private final String errCode;
        private final String message;

        public ErrorResponse(HttpStatus httpStatus, String errCode, String message) {
            this.httpStatus = httpStatus;
            this.errCode = errCode;
            this.message = message;
        }
    }
}
