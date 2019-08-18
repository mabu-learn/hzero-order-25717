package com.hzero.order.infra.repository.impl;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.domain.entity.SoHeader;
import com.hzero.order.domain.repository.HZROodrCompanyRepository;
import com.hzero.order.domain.repository.HZROodrCustomerRepository;
import com.hzero.order.domain.repository.HZROodrSoHeaderRepository;
import io.choerodon.core.exception.CommonException;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HZROodrSoHeaderRepositoryImpl extends BaseRepositoryImpl<SoHeader> implements HZROodrSoHeaderRepository {

    @Autowired
    private HZROodrCompanyRepository HZROodrCompanyRepository;
    @Autowired
    private HZROodrCustomerRepository HZROodrCustomerRepository;



    @Override
    public SoHeader getOrderHeaderInfo(SoHeader soHeader) {

        return this.selectOne(soHeader);
    }



    @Override
    public SoHeader updateOrderHeaderInfo(DetailHeaderDTO detailHeaderDTO, SoHeader soHeaderIncludeId) {
        Long companyId = HZROodrCompanyRepository.getCompanyIdByDto(detailHeaderDTO);
        Long customerId = HZROodrCustomerRepository.getCustomerIdByDto(detailHeaderDTO);
        soHeaderIncludeId.setCompanyId(companyId);
        soHeaderIncludeId.setCustomerId(customerId);
        soHeaderIncludeId.setOrderNumber(detailHeaderDTO.getOrderNumber());
        soHeaderIncludeId.setOrderDate(detailHeaderDTO.getOrderDate());
        soHeaderIncludeId.setOrderStatus(detailHeaderDTO.getOrderStatus());
        this.updateByPrimaryKey(soHeaderIncludeId);

        return soHeaderIncludeId;
    }

    @Override
    public SoHeader insertOrderHeaderInfo(DetailHeaderDTO detailHeaderDTO, Long companyId, Long customerId) {
        SoHeader soHeader = new SoHeader();
        soHeader.setOrderNumber(detailHeaderDTO.getOrderNumber());
        if(this.selectOne(soHeader) != null) {
            throw new CommonException("订单号已存在");
        }
        soHeader.setCompanyId(companyId);
        soHeader.setCustomerId(customerId);
        soHeader.setOrderNumber(detailHeaderDTO.getOrderNumber());
        soHeader.setOrderDate(detailHeaderDTO.getOrderDate());
        soHeader.setOrderStatus(detailHeaderDTO.getOrderStatus());
        this.insert(soHeader);
        return soHeader;
    }



}
