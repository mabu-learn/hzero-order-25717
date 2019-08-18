package com.hzero.order.domain.repository;

import com.hzero.order.api.dto.DetailLineDTO;
import com.hzero.order.domain.entity.SoHeader;
import com.hzero.order.domain.entity.SoLine;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;

import java.util.List;


public interface HZROodrSoLineRepository extends BaseRepository<SoLine> {

    List<SoLine> insertOrderLinesInfo(List<DetailLineDTO> detailLineDTOList, SoHeader soHeader);

    List<SoLine> getSoLineInfoByHeaderId(Long soHeaderId, PageRequest pageRequest);

    List<SoLine> updateOrderLinesInfo(List<DetailLineDTO> detailLineDTOList, SoHeader soHeader);

    SoLine getSoLineInfoByHeaderAndLineNumber(SoLine soLine);
}
