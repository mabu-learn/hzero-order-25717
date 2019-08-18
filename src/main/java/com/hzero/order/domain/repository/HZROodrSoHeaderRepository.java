package com.hzero.order.domain.repository;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.domain.entity.SoHeader;
import org.hzero.mybatis.base.BaseRepository;


public interface HZROodrSoHeaderRepository extends BaseRepository<SoHeader> {

    SoHeader getOrderHeaderInfo(SoHeader soHeader);

    SoHeader updateOrderHeaderInfo(DetailHeaderDTO detailHeaderDTO, SoHeader soHeaderInclude);

    SoHeader insertOrderHeaderInfo(DetailHeaderDTO detailHeaderDTO, Long companyId, Long customerId);

}
