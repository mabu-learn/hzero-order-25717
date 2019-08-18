package com.hzero.order.infra.repository.impl;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.domain.entity.Company;
import com.hzero.order.domain.entity.Customer;
import com.hzero.order.domain.repository.HZROodrCustomerRepository;
import io.choerodon.mybatis.pagehelper.PageHelper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import org.springframework.stereotype.Repository;

@Repository
public class HZROodrCustomerRepositoryImpl extends BaseRepositoryImpl<Customer> implements HZROodrCustomerRepository {


    @Override
    public Page<Customer> pageCustomer(Customer customer, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, ()-> this.select(customer));
    }

    @Override
    public Page<Customer> pageAllCustomer(Company selectedCompany, PageRequest pageRequest) {
        Customer customer = new Customer();
        if(selectedCompany != null ) {
            Long companyId = selectedCompany.getCompanyId();
            customer.setCompanyId(companyId);
            return PageHelper.doPage(pageRequest, ()-> this.select(customer));
        }else {
            return PageHelper.doPage(pageRequest, () -> this.selectAll());
        }
    }

    @Override
    public Long getCustomerIdByDto(DetailHeaderDTO detailHeaderDTO) {
        Customer customerDTO = new Customer();
        customerDTO.setCustomerName(detailHeaderDTO.getCustomerName());
        return this.selectOne(customerDTO).getCustomerId();
    }


}
