package com.iportfolio.readingnnotice.dto.request;

import com.iportfolio.readingnnotice.domain.Notice;
import com.iportfolio.readingnnotice.domain.consts.Activate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@ApiModel
@Getter
public class ModifyNoticeRequest {

    @ApiModelProperty(value = "노출 타입 (1 : 메인에 노출할 특별공지, 0: 일반공지)")
    @Nullable
    private final Integer type;

    @ApiModelProperty(value = "특별공지의 노출 순서")
    @Nullable
    private final Integer idx;

    @ApiModelProperty(value = "공지사항 타이틀, 타이틀은 유일 값")
    @Column(unique = true)
    @Nullable
    @Length(max = 100, message = "title must be at least 100 characters")
    private final String title;

    @ApiModelProperty(value = "공지사항 썸네일")
    @Nullable
    @Length(max = 200, message = "thumbnail must be at least 200 characters")
    private final String thumbnail;

    @ApiModelProperty
    @Nullable
    @Length(max = 500, message = "description must be at least 500 characters")
    private final String description;

    @ApiModelProperty(value = "공지사항 상세 내역")
    @Column(columnDefinition = "longtext")
    @Nullable
    private final String content;

    @ApiModelProperty(value = "공지사항 노출 여부, (0: 비노출, 1: 노출)")
    @NotNull
    private final Integer activate;

    @ApiModelProperty(value = "공지 시작일")
    @Nullable
    private final LocalDateTime startTime;

    @ApiModelProperty(value = "공지 종료일")
    @Nullable
    private final LocalDateTime endTime;

    @Builder
    private ModifyNoticeRequest(@Nullable final Integer type, @Nullable final Integer idx,
        @Nullable final String title, @Nullable final String thumbnail,
        @Nullable final String description, @Nullable final String content,
        final Integer activate, @Nullable final LocalDateTime startTime,
        @Nullable final LocalDateTime endTime) {

        this.type = type;
        this.idx = idx;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
        this.activate = activate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Notice toNoticeEntity() {
        return Notice.builder()
            .type(type)
            .idx(idx)
            .title(title)
            .thumbnail(thumbnail)
            .description(description)
            .content(content)
            .activate(Activate.valueOf(activate))
            .sendTime(startTime)
            .endTime(endTime)
            .build();
    }
}
