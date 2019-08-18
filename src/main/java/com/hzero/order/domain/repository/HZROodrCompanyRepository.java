package com.hzero.order.domain.repository;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.domain.entity.Company;
import org.hzero.mybatis.base.BaseRepository;


public interface HZROodrCompanyRepository extends BaseRepository<Company> {

    Long getCompanyIdByDto(DetailHeaderDTO detailHeaderDTO);
}
