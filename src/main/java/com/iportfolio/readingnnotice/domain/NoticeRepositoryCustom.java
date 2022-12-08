package com.iportfolio.readingnnotice.domain;

import com.iportfolio.readingnnotice.domain.consts.Activate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> findByKeyword(final Pageable pageable, final String keyword, final Activate activate);
}
