package com.iportfolio.readingnnotice.dto.response;

import com.iportfolio.readingnnotice.domain.Notice;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeDetailResponse implements Serializable {

    private static final long serialVersionUID = -5147728631409985547L;

    private final Long id;

    private final Integer type;

    private final Integer idx;

    private final String title;

    private final String content;

    private final String thumbnail;

    private final String description;

    private final Integer activate;

    private final LocalDateTime regTime;

    public NoticeDetailResponse(final Long id, final Integer type, final Integer idx, final String title,
        final String content, final String thumbnail, final String description, final Integer activate,
        final LocalDateTime regTime) {

        this.id = id;
        this.type = type;
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.description = description;
        this.activate = activate;
        this.regTime = regTime;
    }

    public static NoticeDetailResponse from(final Notice notice) {
        return new NoticeDetailResponse(
            notice.getId(),
            notice.getType(),
            notice.getIdx(),
            notice.getTitle(),
            notice.getContent(),
            notice.getThumbnail(),
            notice.getDescription(),
            notice.getActivate().getCode(),
            notice.getRegTime()
        );
    }
}
