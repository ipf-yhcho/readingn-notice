package com.iportfolio.readingnnotice.domain;

import com.iportfolio.readingnnotice.domain.consts.Activate;
import com.iportfolio.readingnnotice.domain.consts.PushStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.lang.Nullable;

/**
 * CREATE TABLE `t_notice` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key\n',
 *   `type` int(11) NOT NULL DEFAULT '0' COMMENT '노출 타입\\n1 : 메인에 노출할 특별공지\\n0: 일반공지',
 *   `idx` int(11) NOT NULL DEFAULT '0' COMMENT '특별공지의 노출 순서',
 *   `title` varchar(100) NOT NULL COMMENT '공지사항 타이틀\n\n타이틀은 유일 값\n',
 *   `thumbnail` varchar(200) DEFAULT NULL,
 *   `description` varchar(500) DEFAULT NULL,
 *   `content` longtext NOT NULL COMMENT '공지사항 상세 내역\n\nHTML 에디터로 작성하여 데이터가 매우 큼\n',
 *   `activate` tinyint(4) NOT NULL DEFAULT '0' COMMENT '공지사항 노출 여부\n\n0: 비노출\n1: 노출\n\n\n',
 *   `reg_time` datetime NOT NULL COMMENT '등록 일자\n',
 *   `start_time` datetime DEFAULT NULL,
 *   `end_time` datetime DEFAULT NULL,
 *   `send_time` datetime DEFAULT NULL COMMENT '공지사항 푸시 전송 요청 시각',
 *   `send_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '공지사항 푸시 전송 결과 (-1: 실패, 0: 미발송, 1: 성공, 2: 발송중)',
 *   `send_target` varchar(10) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=597 DEFAULT CHARSET=utf8;
 */

@Entity
@Table(name = "t_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "int(11)")
    @Comment("노출 타입 (1 : 메인에 노출할 특별공지, 0: 일반공지)")
    @NotNull
    private Integer type;

    @Column(columnDefinition = "int(11)")
    @Comment("특별공지의 노출 순서")
    @NotNull
    private Integer idx;

    @Column(length = 100)
    @Comment("공지사항 타이틀 타이틀은 유일 값")
    @NotNull
    private String title;

    @Column(length = 200)
    @Nullable
    private String thumbnail;

    @Column(length = 500)
    @Nullable
    private String description;

    @Column(columnDefinition = "longtext")
    @Comment("공지사항 상세 내역 HTML 에디터로 작성하여 데이터가 매우 큼")
    @NotNull
    private String content;

    @Column(columnDefinition = "tinyint(4) default 0")
    @Comment("공지사항 노출 여부 (0: 비노출, 1: 노출)")
    @NotNull
    private Activate activate;

    @Column(columnDefinition = "datetime")
    @Comment("등록 일자")
    @NotNull
    private LocalDateTime regTime;

    @Column(columnDefinition = "datetime")
    @Nullable
    private LocalDateTime startTime;

    @Column(columnDefinition = "datetime")
    @Nullable
    private LocalDateTime endTime;

    @Column(columnDefinition = "datetime")
    @Comment("공지사항 푸시 전송 요청 시각")
    @Nullable
    private LocalDateTime sendTime;

    @Column(columnDefinition = "tinyint(4) default 0")
    @Comment("공지사항 푸시 전송 결과 (-1: 실패, 0: 미발송, 1: 성공, 2: 발송중)")
    @NotNull
    private PushStatus sendStatus;

    @Column(length = 10)
    @Nullable
    private String sendTarget;

    @Builder
    private Notice(final Long id, final Integer type, final Integer idx, final String title, final String thumbnail,
        final String description, final String content, final Activate activate, final LocalDateTime regTime,
        @Nullable final LocalDateTime startTime, @Nullable final LocalDateTime endTime,
        @Nullable final LocalDateTime sendTime, final PushStatus sendStatus, final String sendTarget) {

        this.id = id;
        this.type = type;
        this.idx = idx;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
        this.activate = activate;
        this.regTime = regTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sendTime = sendTime;
        Optional.ofNullable(sendStatus)
            .ifPresentOrElse(status -> this.sendStatus = status, () -> this.sendStatus = PushStatus.UNSENT);
        this.sendTarget = sendTarget;
    }

    public void modifyFields(Notice modifiedNotice) {
        Optional.ofNullable(modifiedNotice.getType()).ifPresent(type -> this.type = type);
        Optional.ofNullable(modifiedNotice.getIdx()).ifPresent(idx -> this.idx = idx);
        Optional.ofNullable(modifiedNotice.getTitle()).ifPresent(title -> this.title = title);
        Optional.ofNullable(modifiedNotice.getThumbnail()).ifPresent(thumbnail -> this.thumbnail = thumbnail);
        Optional.ofNullable(modifiedNotice.getDescription()).ifPresent(description -> this.description = description);
        Optional.ofNullable(modifiedNotice.getContent()).ifPresent(content -> this.content = content);
        Optional.ofNullable(modifiedNotice.getActivate()).ifPresent(activate -> this.activate = activate);
        Optional.ofNullable(modifiedNotice.getStartTime()).ifPresent(startTime -> this.startTime = startTime);
        Optional.ofNullable(modifiedNotice.getEndTime()).ifPresent(endTime -> this.endTime = endTime);
    }
}
