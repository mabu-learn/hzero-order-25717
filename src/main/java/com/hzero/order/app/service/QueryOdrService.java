package com.hzero.order.app.service;

import com.hzero.order.api.dto.SelectPO;
import com.hzero.order.domain.entity.SoHeader;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.List;

public interface QueryOdrService {

    List<SelectPO> queryOrderByCondition(SelectPO selectPO);
//
//    List<Company> queryCompanyByNumberOrName(String companyNumber, String companyName);
//
//    List<Customer> queryCustomerByNumberOrName(String customerNumber, String customerName);

    List queryOrderHeaderAndLine(SoHeader soHeader, PageRequest pageRequest);
}
