package com.iportfolio.readingnnotice.dto.response.template;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingInfo {

    private final Integer pageNumber;

    private final Integer pageSize;

    private final Integer totalPage;

    private final Long totalElement;

    public PagingInfo(final Integer pageNumber, final Integer pageSize, final Integer totalPage,
        final Long totalElement) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalElement = totalElement;
    }
}
