package com.hzero.order.app.service.impl;

import com.hzero.order.api.dto.SelectPO;
import com.hzero.order.app.service.QueryOdrService;
import com.hzero.order.domain.entity.SoHeader;
import com.hzero.order.domain.entity.SoLine;
import com.hzero.order.domain.repository.HZROodrSoHeaderRepository;
import com.hzero.order.domain.repository.HZROodrSoLineRepository;
import com.hzero.order.infra.mapper.HZROodrSoHeaderMapper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryOdrServiceImpl implements QueryOdrService {

    @Autowired
    private HZROodrSoHeaderMapper HZROodrSoHeaderMapper;
    @Autowired
    private HZROodrSoHeaderRepository HZROodrSoHeaderRepository;
    @Autowired
    private HZROodrSoLineRepository HZROodrSoLineRepository;


    @Override
    public List<SelectPO> queryOrderByCondition(SelectPO selectPO) {
        return HZROodrSoHeaderMapper.queryOrderByCondition(selectPO);
    }



//    @Override
//    public List<Company> queryCompanyByNumberOrName(String companyNumber, String companyName) {
//        return orderSelectMapper.queryCompanyByNumberOrName(companyNumber,companyName);
//    }
//
//    @Override
//    public List<Customer> queryCustomerByNumberOrName(String customerNumber, String customerName) {
//        return orderSelectMapper.queryCustomerByNumberOrName(customerNumber,customerName);
//    }

    @Override
    public List queryOrderHeaderAndLine(SoHeader soHeader, PageRequest pageRequest) {
        ArrayList orderHeaderAndLineList = new ArrayList();
        SoHeader soHeaderInfo = HZROodrSoHeaderRepository.getOrderHeaderInfo(soHeader); //根据订单号拿到订单头信息
        Long soHeaderId = soHeaderInfo.getSoHeaderId();//从订单头信息中拿到订单头Id
        soHeaderInfo.setSoHeaderId(soHeaderId); //将订单头Id注入订单头对象和订单行对象中
        List<SoLine> soLines = HZROodrSoLineRepository.getSoLineInfoByHeaderId(soHeaderId,pageRequest);//根据订单头Id查询订单行信息

        orderHeaderAndLineList.add(soHeader);
        orderHeaderAndLineList.add(soLines);
        return orderHeaderAndLineList;
    }


}
