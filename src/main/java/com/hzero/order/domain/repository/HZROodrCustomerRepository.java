package com.hzero.order.domain.repository;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.domain.entity.Company;
import com.hzero.order.domain.entity.Customer;
import org.hzero.mybatis.base.BaseRepository;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;


public interface HZROodrCustomerRepository extends BaseRepository<Customer> {

    Page<Customer> pageCustomer(Customer customer, PageRequest pageRequest);

    Page<Customer> pageAllCustomer(Company company, PageRequest pageRequest);

    Long getCustomerIdByDto(DetailHeaderDTO detailHeaderDTO);
}
