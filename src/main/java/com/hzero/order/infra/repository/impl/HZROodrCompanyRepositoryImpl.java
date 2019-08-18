package com.hzero.order.infra.repository.impl;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.domain.entity.Company;
import com.hzero.order.domain.repository.HZROodrCompanyRepository;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;


@Repository
public class HZROodrCompanyRepositoryImpl extends BaseRepositoryImpl<Company> implements HZROodrCompanyRepository {
//
//    @Override
//    public Company queryCompanyByNameAndNumber(Company hodrCompany) {
//        return this.selectOne(hodrCompany);
//    }

    @Override
    public Long getCompanyIdByDto(DetailHeaderDTO detailHeaderDTO) {
        Company companyDTO = new Company();
        companyDTO.setCompanyName(detailHeaderDTO.getCompanyName());
        return this.selectOne(companyDTO).getCompanyId();
    }
}
