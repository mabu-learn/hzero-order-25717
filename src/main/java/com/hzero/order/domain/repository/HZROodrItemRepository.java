package com.hzero.order.domain.repository;

import com.hzero.order.domain.entity.Item;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.base.BaseRepository;


public interface HZROodrItemRepository extends BaseRepository<Item> {

    Item getHodrItemInfo(Item item);

    Page<Item> pageItem(Item item, PageRequest pageRequest);
}
