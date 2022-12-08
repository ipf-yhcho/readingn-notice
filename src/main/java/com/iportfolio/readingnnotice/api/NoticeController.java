package com.iportfolio.readingnnotice.api;

import static com.iportfolio.readingnnotice.domain.consts.Activate.ACTIVATED;

import com.iportfolio.readingnnotice.business.NoticeService;
import com.iportfolio.readingnnotice.dto.request.CreateNoticeRequest;
import com.iportfolio.readingnnotice.dto.request.ModifyNoticeRequest;
import com.iportfolio.readingnnotice.dto.response.NoticeDetailResponse;
import com.iportfolio.readingnnotice.dto.response.NoticeSimpleResponses;
import com.iportfolio.readingnnotice.dto.response.template.ApiResponse;
import com.iportfolio.readingnnotice.dto.response.template.ResponseCode;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(final NoticeService noticeService) {
        log.info("{} Constructor called..", this.getClass().getName());
        this.noticeService = noticeService;
    }

    /**
     * @param page
     * @param limit
     * @param keyword
     * @return
     */
    @ApiOperation(
        value = "공지사항 리스트 조회 / 키워드 검색"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<NoticeSimpleResponses> getActivatedNotices(
        @RequestParam(defaultValue = "1") final Integer page,
        @RequestParam(defaultValue = "10") final Integer limit,
        final String keyword) {

        NoticeSimpleResponses response = noticeService.getActivatedNotices(
            PageRequest.of(page - 1, limit), keyword, ACTIVATED);

        return new ApiResponse<>(response, ResponseCode.OK);
    }

    /**
     * @param noticeId
     * @return
     */
    @ApiOperation(
        value = "공지사항 상세 조회"
    )
    @GetMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<NoticeDetailResponse> getNoticeDetail(@PathVariable final Long noticeId) {
        NoticeDetailResponse response = noticeService.getNoticeDetail(noticeId);

        return new ApiResponse<>(response, ResponseCode.OK);
    }

    /**
     * @param createNoticeRequest
     * @return
     */
    @ApiOperation(
        value = "공지사항 생성"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> createNotice(@Valid @RequestBody final CreateNoticeRequest createNoticeRequest) {
        noticeService.createNotice(createNoticeRequest);

        return new ApiResponse<>(null, ResponseCode.CREATED);
    }

    /**
     * @param noticeId
     * @param modifyNoticeRequest
     * @return
     */
    @ApiOperation(
        value = "공지사항 수정"
    )
    @PatchMapping("/{noticeId}")
    public ApiResponse<Void> modifyNotice(@PathVariable final Long noticeId,
        @Valid @RequestBody final ModifyNoticeRequest modifyNoticeRequest) {

        noticeService.modifyNotice(noticeId, modifyNoticeRequest);

        return new ApiResponse<>(null, ResponseCode.OK);
    }

    /**
     * @param noticeId
     * @return
     */
    @ApiOperation(
        value = "공지사항 삭제"
    )
    @DeleteMapping("/{noticeId}")
    public ApiResponse<Void> deleteNotice(@PathVariable final Long noticeId) {
        noticeService.deleteNotice(noticeId);

        return new ApiResponse<>(null, ResponseCode.OK);
    }
}
