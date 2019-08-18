package com.hzero.order.infra.repository.impl;

import com.hzero.order.domain.entity.Item;
import com.hzero.order.domain.repository.HZROodrItemRepository;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class HZROodrItemRepositoryImpl extends BaseRepositoryImpl<Item> implements HZROodrItemRepository {
    @Override
    public Page<Item> pageItem(Item item, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, ()-> new HZROodrItemRepositoryImpl().select(item));
    }

    @Override
    public Item getHodrItemInfo(Item item) {
        return this.selectOne(item);
    }
}
