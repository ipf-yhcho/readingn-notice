package com.iportfolio.readingnnotice.dto.response.template;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -5147774756823077751L;

    private final T data;

    private final Integer status;

    private final String message;

    private final LocalDateTime sendTime;

    public ApiResponse(final T data, final ResponseCode responseCode) {
        this.data = data;
        this.status = responseCode.getStatus();
        this.message = responseCode.getMessage();
        this.sendTime = LocalDateTime.now();
    }
}
