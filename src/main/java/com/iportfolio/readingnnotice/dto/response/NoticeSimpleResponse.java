package com.iportfolio.readingnnotice.dto.response;

import com.iportfolio.readingnnotice.domain.Notice;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeSimpleResponse implements Serializable {

    private static final long serialVersionUID = 8873608209660006156L;

    private final Long id;

    private final String title;

    private final String content;

    private final Integer activate;

    private final LocalDateTime regDateTime;

    public NoticeSimpleResponse(final Long id, final String title, final String content, final Integer activate,
        final LocalDateTime regDateTime) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.activate = activate;
        this.regDateTime = regDateTime;
    }

    public static NoticeSimpleResponse from(final Notice notice) {
        return new NoticeSimpleResponse(
            notice.getId(),
            notice.getTitle(),
            notice.getContent(),
            notice.getActivate().getCode(),
            notice.getRegTime()
        );
    }
}
