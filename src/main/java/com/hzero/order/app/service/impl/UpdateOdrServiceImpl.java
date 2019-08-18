package com.hzero.order.app.service.impl;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.api.dto.DetailLineDTO;
import com.hzero.order.app.service.UpdateOdrService;
import com.hzero.order.domain.entity.SoHeader;
import com.hzero.order.domain.repository.HZROodrSoHeaderRepository;
import com.hzero.order.domain.repository.HZROodrSoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateOdrServiceImpl implements UpdateOdrService {

    @Autowired
    private HZROodrSoHeaderRepository HZROodrSoHeaderRepository;
    @Autowired
    private HZROodrSoLineRepository HZROodrSoLineRepository;

    @Override
    public List updateOrder(DetailHeaderDTO detailHeaderDTO,
                            List<DetailLineDTO> detailLineDTOList, String orderNumber) {
        SoHeader soHeader = new SoHeader();
        soHeader.setOrderNumber(orderNumber);
        SoHeader soHeaderIncludeId = HZROodrSoHeaderRepository.getOrderHeaderInfo(soHeader);
        HZROodrSoHeaderRepository.updateOrderHeaderInfo(detailHeaderDTO, soHeaderIncludeId);
        HZROodrSoLineRepository.updateOrderLinesInfo(detailLineDTOList, soHeaderIncludeId);
        return null;
    }
}
