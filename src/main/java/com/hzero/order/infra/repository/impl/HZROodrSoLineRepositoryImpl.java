package com.hzero.order.infra.repository.impl;

import com.hzero.order.api.dto.DetailLineDTO;
import com.hzero.order.domain.entity.Item;
import com.hzero.order.domain.entity.SoHeader;
import com.hzero.order.domain.entity.SoLine;
import com.hzero.order.domain.repository.HZROodrItemRepository;
import com.hzero.order.domain.repository.HZROodrSoHeaderRepository;
import com.hzero.order.domain.repository.HZROodrSoLineRepository;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HZROodrSoLineRepositoryImpl extends BaseRepositoryImpl<SoLine> implements HZROodrSoLineRepository {

    @Autowired
    private HZROodrSoHeaderRepository HZROodrSoHeaderRepository;
    @Autowired
    private HZROodrItemRepository HZROodrItemRepository;
    @Override
    public List<SoLine> getSoLineInfoByHeaderId(Long soHeaderId, PageRequest pageRequest) {
        SoLine soLine = new SoLine();
        soLine.setSoHeaderId(soHeaderId);
        return PageHelper.doPage(pageRequest, ()-> this.select(soLine));
    }

    @Override
    public SoLine getSoLineInfoByHeaderAndLineNumber(SoLine soLine) {

        return this.selectOne(soLine);
    }

    @Override
    public List<SoLine> updateOrderLinesInfo(List<DetailLineDTO> detailLineDTOLists, SoHeader soHeaderIncludeId) {
        ArrayList<SoLine> soLines = new ArrayList();
        SoLine soLine = new SoLine();
        Item item = new Item();
        for (DetailLineDTO o: detailLineDTOLists) {
            soLine.setLineNumber(o.getLineNumber());
            soLine.setSoHeaderId(soHeaderIncludeId.getSoHeaderId());
            SoLine soLineIncludeId = this.getSoLineInfoByHeaderAndLineNumber(soLine);
            item.setItemCode(o.getItemCode());
            item.setItemDescription(o.getItemDescription());
            Item itemIncludeId = HZROodrItemRepository.getHodrItemInfo(item);//根据物料编码和描述拿到物料对象，进而拿到物料Id
            soLine.setSoLineId(soLineIncludeId.getSoLineId());
            soLine.setItemId(itemIncludeId.getItemId());
            soLine.setLineNumber(o.getLineNumber());
            soLine.setOrderQuantity(o.getOrderQuantity());
            soLine.setOrderQuantityUom(o.getOrderQuantityUom());
            soLine.setUnitSellingPrice(o.getUnitSellingPrice());
            soLine.setDecription(o.getDecription());
            soLine.setAddition1(o.getAddition1());
            soLine.setAddition2(o.getAddition2());
            soLine.setAddition3(o.getAddition3());
            soLine.setAddition4(o.getAddition4());
            soLine.setAddition5(o.getAddition5());
            this.updateByPrimaryKey(soLine);
            soLines.add(soLine);
        }

        return soLines;
    }

    @Override
    public List<SoLine> insertOrderLinesInfo(List<DetailLineDTO> detailLineDTOList, SoHeader soHeader) {
        ArrayList<SoLine> soLines = new ArrayList<>();
        SoLine soLine = new SoLine();
        Item item = new Item();
        SoHeader soHeaderIncludeId = HZROodrSoHeaderRepository.getOrderHeaderInfo(soHeader);//根据订单头对象拿到订单头Id
        for (DetailLineDTO o: detailLineDTOList) {
            item.setItemCode(o.getItemCode());
            item.setItemDescription(o.getItemDescription());
            Item itemIncludeId = HZROodrItemRepository.getHodrItemInfo(item);//根据物料编码和描述拿到物料对象，进而拿到物料Id
            soLine.setSoHeaderId(soHeaderIncludeId.getSoHeaderId());
            soLine.setItemId(itemIncludeId.getItemId());
            soLine.setLineNumber(o.getLineNumber());
            soLine.setOrderQuantity(o.getOrderQuantity());
            soLine.setOrderQuantityUom(o.getOrderQuantityUom());
            soLine.setUnitSellingPrice(o.getUnitSellingPrice());
            soLine.setDecription(o.getDecription());
            soLine.setAddition1(o.getAddition1());
            soLine.setAddition2(o.getAddition2());
            soLine.setAddition3(o.getAddition3());
            soLine.setAddition4(o.getAddition4());
            soLine.setAddition5(o.getAddition5());
            this.insert(soLine);
            soLines.add(soLine);
        }
        return soLines;
    }


}
