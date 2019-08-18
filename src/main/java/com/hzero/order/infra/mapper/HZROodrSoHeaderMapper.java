package com.hzero.order.infra.mapper;

import com.hzero.order.api.dto.SelectPO;
import com.hzero.order.domain.entity.SoHeader;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.List;


public interface HZROodrSoHeaderMapper extends BaseMapper<SoHeader> {
    List<SelectPO> queryOrderByCondition(SelectPO selectPO);
}
