package com.hzero.order.api.controller.v1;

import com.hzero.order.api.dto.SelectPO;
import com.hzero.order.app.service.QueryOdrService;
import com.hzero.order.config.SwaggerConfig;
import com.hzero.order.domain.entity.Company;
import com.hzero.order.domain.entity.Customer;
import com.hzero.order.domain.entity.Item;
import com.hzero.order.domain.repository.HZROodrCompanyRepository;
import com.hzero.order.domain.repository.HZROodrCustomerRepository;
import com.hzero.order.domain.repository.HZROodrItemRepository;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(SwaggerConfig.TOTAL)
@RestController("orderTotalController.v1")
@RequestMapping("/v1/orderTotal")
public class OrderControllerAll {

    @Autowired
    private QueryOdrService queryOdrService;
    @Autowired
    private HZROodrCompanyRepository HZROodrCompanyRepository;
    @Autowired
    private HZROodrCustomerRepository HZROodrCustomerRepository;
    @Autowired
    private HZROodrItemRepository HZROodrItemRepository;

//    订单汇总查询

    @ApiOperation(value = "根据选定条件查询订单信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping
    public ResponseEntity<List<SelectPO>> queryOrderByCondition(
              SelectPO selectPO) {
        return Results.success(queryOdrService.queryOrderByCondition(selectPO));
    }

    @ApiOperation(value = "根据公司代码或名称查询公司信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/company")
    public ResponseEntity<Company> queryCompanyByNumberOrName(
            Company company, HttpSession session) {
        session.setAttribute("selectedCompany", company);
        return Results.success(HZROodrCompanyRepository.selectOne(company));
    }

    @ApiOperation(value = "根据客户编码或名称查询客户信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/customer")
    public ResponseEntity<List<Customer>> queryCustomerByNumberOrName(
            Customer customer, PageRequest pageRequest) {
        return Results.success(HZROodrCustomerRepository.pageCustomer(customer, pageRequest));
    }

    @ApiOperation(value = "根据物料描述或编码查询物料信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/item")
    public ResponseEntity<List<Item>> queryItemByCodeOrDescription(
            Item item, PageRequest pageRequest) {
        return Results.success(HZROodrItemRepository.pageItem(item, pageRequest));
    }

    @ApiOperation(value = "查询所有公司代码和名称信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/allCompany")
    public ResponseEntity<List<Company>> queryAllCompany(PageRequest pageRequest) {
        return Results.success(PageHelper.doPage(pageRequest, ()-> HZROodrCompanyRepository.selectAll()));
    }

    @ApiOperation(value = "根据公司Id或名称查询客户信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/allCustomer")
    public ResponseEntity<List<Customer>> queryAllCustomer(
            PageRequest pageRequest, HttpSession session) {
        Company selectedCompany = (Company) session.getAttribute("selectedCompany");
        return Results.success(HZROodrCustomerRepository.pageAllCustomer(selectedCompany, pageRequest));
    }

    @ApiOperation(value = "根据公司Id或名称查询物料信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/allItem")
    public ResponseEntity<List<Item>> queryAllItem(PageRequest pageRequest) {
        return Results.success(PageHelper.doPage(pageRequest, ()-> HZROodrItemRepository.selectAll()));
    }
}
