package com.iportfolio.readingnnotice.dto.response;


import com.iportfolio.readingnnotice.domain.Notice;
import com.iportfolio.readingnnotice.dto.response.template.PagingInfo;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@ToString
public class NoticeSimpleResponses implements Serializable {

    private static final long serialVersionUID = -7652374761863763491L;

    private final List<NoticeSimpleResponse> noticeList;

    private final PagingInfo pageInfo;

    public NoticeSimpleResponses(final List<Notice> notices, final Integer pageNumber, final Integer pageSize,
        final Integer totalPage, final Long totalElement) {

        this.noticeList = notices.stream()
            .map(NoticeSimpleResponse::from)
            .collect(Collectors.toUnmodifiableList());

        this.pageInfo = new PagingInfo(pageNumber, pageSize, totalPage, totalElement);
    }

    public static NoticeSimpleResponses from(final Page<Notice> page) {
        return new NoticeSimpleResponses(
            page.getContent(),
            page.getNumber() + 1,
            page.getSize(),
            page.getTotalPages(),
            page.getTotalElements()
        );
    }
}
