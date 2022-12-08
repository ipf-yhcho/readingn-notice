package com.iportfolio.readingnnotice.dto.request;

import com.iportfolio.readingnnotice.domain.Notice;
import com.iportfolio.readingnnotice.domain.consts.Activate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@ApiModel
@Getter
public class CreateNoticeRequest {

    @ApiModelProperty(value = "노출 타입 (1 : 메인에 노출할 특별공지, 0: 일반공지)")
    @NotNull(message = "type cannot be empty")
    private final Integer type;

    @ApiModelProperty(value = "특별공지의 노출 순서")
    @NotNull(message = "idx cannot be empty")
    private final Integer idx;

    @ApiModelProperty(value = "공지사항 타이틀, 타이틀은 유일 값")
    @NotBlank(message = "title cannot be empty")
    @Length(max = 100, message = "title can be up to 100 characters")
    private final String title;

    @ApiModelProperty(value = "공지사항 썸네일")
    @Nullable
    @Length(max = 200, message = "thumbnail can be up to 200 characters")
    private final String thumbnail;

    @ApiModelProperty
    @Nullable
    @Length(max = 500, message = "description can be up to 500 characters")
    private final String description;

    @ApiModelProperty(value = "공지사항 상세 내역")
    @NotBlank(message = "content cannot be empty")
    private final String content;

    @ApiModelProperty(value = "공지사항 노출 여부, (0: 비노출, 1: 노출)")
    @NotNull(message = "activate cannot be empty")
    private final Integer activate;

    @ApiModelProperty(value = "공지 시작일")
    @Nullable
    private final LocalDateTime startTime;

    @ApiModelProperty(value = "공지 종료일")
    @Nullable
    private final LocalDateTime endTime;

    @Builder
    private CreateNoticeRequest(final Integer type, final Integer idx, final String title,
        @Nullable final String thumbnail, @Nullable final String description, final String content,
        final Integer activate, @Nullable final LocalDateTime startTime, @Nullable final LocalDateTime endTime) {

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
        LocalDateTime regTime = LocalDateTime.now();

        return Notice.builder()
            .type(type)
            .idx(idx)
            .title(title)
            .thumbnail(thumbnail)
            .description(description)
            .content(content)
            .activate(Activate.valueOf(activate))
            .regTime(regTime)
            .startTime(startTime)
            .endTime(endTime)
            .build();
    }
}
