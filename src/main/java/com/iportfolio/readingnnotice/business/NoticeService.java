package com.iportfolio.readingnnotice.business;

import com.iportfolio.readingnnotice.domain.Notice;
import com.iportfolio.readingnnotice.domain.NoticeRepository;
import com.iportfolio.readingnnotice.domain.consts.Activate;
import com.iportfolio.readingnnotice.dto.request.CreateNoticeRequest;
import com.iportfolio.readingnnotice.dto.request.ModifyNoticeRequest;
import com.iportfolio.readingnnotice.dto.response.NoticeDetailResponse;
import com.iportfolio.readingnnotice.dto.response.NoticeSimpleResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(final NoticeRepository noticeRepository) {
        log.info("{} Constructor called..", this.getClass().getName());
        this.noticeRepository = noticeRepository;
    }

    /**
     * @param pageable
     * @param keyword
     * @param activate
     * @return
     */
    @Transactional(readOnly = true)
    public NoticeSimpleResponses getActivatedNotices(final Pageable pageable, final String keyword,
        final Activate activate) {

        return NoticeSimpleResponses.from(noticeRepository.findByKeyword(pageable, keyword, activate));
    }

    /**
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public NoticeDetailResponse getNoticeDetail(@PathVariable final Long postId) {
        Notice notice = noticeRepository.findById(postId)
            .orElseThrow(RuntimeException::new);

        return NoticeDetailResponse.from(notice);
    }

    /**
     * @param createNoticeRequest
     */
    public void createNotice(@Valid @RequestBody CreateNoticeRequest createNoticeRequest) {
        Notice notice = noticeRepository.save(createNoticeRequest.toNoticeEntity());
        log.debug("NoticeService.createNotice: {} is created", notice);
    }

    /**
     * @param postId
     * @param modifyNoticeRequest
     */
    public void modifyNotice(@PathVariable Long postId, @Valid @RequestBody ModifyNoticeRequest modifyNoticeRequest) {
        Notice notice = noticeRepository.findById(postId)
            .orElseThrow(RuntimeException::new);

        notice.modifyFields(modifyNoticeRequest.toNoticeEntity());
        log.debug("NoticeService.modifyNotice: {} is modified", notice);
    }

    /**
     * @param postId
     */
    public void deleteNotice(@PathVariable Long postId) {
        noticeRepository.deleteById(postId);
    }
}
