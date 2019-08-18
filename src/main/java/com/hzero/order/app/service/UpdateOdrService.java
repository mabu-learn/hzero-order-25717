package com.hzero.order.app.service;

import com.hzero.order.api.dto.DetailHeaderDTO;
import com.hzero.order.api.dto.DetailLineDTO;

import java.util.List;

public interface UpdateOdrService {

    List updateOrder(DetailHeaderDTO detailHeaderDTO,
                     List<DetailLineDTO> detailLineDTOList, String orderNumber);
}