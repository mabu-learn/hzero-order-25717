package com.hzero.order.app.service.impl;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.api.dto.DetailLineDTO;
import com.hzero.order.app.service.CreateOdrService;
import com.hzero.order.domain.entity.SoHeader;
import com.hzero.order.domain.repository.HZROodrCompanyRepository;
import com.hzero.order.domain.repository.HZROodrCustomerRepository;
import com.hzero.order.domain.repository.HZROodrSoHeaderRepository;
import com.hzero.order.domain.repository.HZROodrSoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOdrServiceImpl implements CreateOdrService {

    @Autowired
    private HZROodrCompanyRepository HZROodrCompanyRepository;
    @Autowired
    private HZROodrCustomerRepository HZROodrCustomerRepository;
    @Autowired
    private HZROodrSoHeaderRepository HZROodrSoHeaderRepository;
    @Autowired
    private HZROodrSoLineRepository HZROodrSoLineRepository;

    @Override
    public List createOrder(DetailHeaderDTO detailHeaderDTO,
                            List<DetailLineDTO> detailLineDTOList) {
        Long companyId = HZROodrCompanyRepository.getCompanyIdByDto(detailHeaderDTO);
        Long customerId = HZROodrCustomerRepository.getCustomerIdByDto(detailHeaderDTO);
        SoHeader soHeader = HZROodrSoHeaderRepository.
                insertOrderHeaderInfo(detailHeaderDTO, companyId, customerId);
        HZROodrSoLineRepository.insertOrderLinesInfo(detailLineDTOList, soHeader);

        return null;
    }
}
