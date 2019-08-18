package com.hzero.order.api.controller.v1;


import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.api.dto.DetailLineDTO;
import com.hzero.order.app.service.CreateOdrService;
import com.hzero.order.app.service.QueryOdrService;
import com.hzero.order.app.service.UpdateOdrService;
import com.hzero.order.config.SwaggerConfig;
import com.hzero.order.domain.entity.SoHeader;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(SwaggerConfig.DETAIL)
@RestController("orderDetailController.v1")
@RequestMapping("/v1/orderDetail")
public class OrderControllerDetail {

    @Autowired
    private QueryOdrService queryOdrService;
    @Autowired
    private CreateOdrService createOdrService;
    @Autowired
    private UpdateOdrService updateOdrService;

//    订单明细界面

    @ApiOperation(value = "根据订单头编号查询订单头信息和订单行信息")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/orderHeaderAndLineInfo")
    public ResponseEntity<List> queryOrderHeaderAndLines(
            SoHeader soHeader, PageRequest pageRequest, HttpSession session) {
        session.setAttribute("orderNumber", soHeader);
        return Results.success(queryOdrService.queryOrderHeaderAndLine(soHeader, pageRequest));
    }

    @ApiOperation(value = "新增订单")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/addOrder")
    public ResponseEntity<List> addOrder(@RequestBody DetailHeaderDTO detailHeaderDTO,
                                    List<DetailLineDTO> detailLineDTOList) {
        return Results.success(createOdrService.createOrder(detailHeaderDTO, detailLineDTOList));
    }

    @ApiOperation(value = "更新订单")
    @Permission(level = ResourceLevel.SITE, permissionLogin = true)
    @GetMapping("query/modifyOrder")
    public ResponseEntity modifyOrder(DetailHeaderDTO detailHeaderDTO,
                                      List<DetailLineDTO> detailLineDTOList, HttpSession session) {
        String orderNumber = (String) session.getAttribute("orderNumber");
        return Results.success(updateOdrService.updateOrder(detailHeaderDTO, detailLineDTOList, orderNumber));
    }
}
