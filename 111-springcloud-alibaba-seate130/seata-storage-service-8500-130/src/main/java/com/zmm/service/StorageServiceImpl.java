package com.zmm.service;

import com.zmm.entity.Storage;
import com.zmm.mapper.StorageMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService{

    @Resource
    StorageMapper storageMapper;

    @Override
    public int decrease(Long productId, Integer count) {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());

        log.info("------->storage-service中扣减库存开始");
        log.info("------->storage-com.zmm.service 开始查询商品是否存在");
        Storage storage = storageMapper.selectByProductId(productId);
        if (storage != null && storage.getResidue().intValue() >= count.intValue()) {
            Storage storage2 = new Storage();
            storage2.setProductId(productId);
            storage.setUsed(storage.getUsed() + count);
            storage.setResidue(storage.getTotal().intValue() - storage.getUsed());
            int decrease = storageMapper.decrease(storage);
            log.info("------->storage-com.zmm.service 扣减库存成功");
            return decrease;
        } else {
            log.info("------->storage-com.zmm.service 库存不足，开始回滚！");
            throw new RuntimeException("库存不足，扣减库存失败！");
        }
    }
}
