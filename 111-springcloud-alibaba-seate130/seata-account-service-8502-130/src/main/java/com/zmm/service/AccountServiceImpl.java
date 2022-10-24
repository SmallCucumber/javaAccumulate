package com.zmm.service;

import com.zmm.entity.Account;
import com.zmm.mapper.AccountMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Resource
    AccountMapper accountMapper;

    @Override
    public int decrease(Long userId, BigDecimal money) {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());

        log.info("------->account-com.zmm.service 开始查询账户余额");
        Account account = accountMapper.selectByUserId(userId);
        log.info("------->account-com.zmm.service 账户余额查询完成，" + account);
        if (account != null && account.getResidue().intValue() >= money.intValue()) {
            log.info("------->account-com.zmm.service 开始从账户余额中扣钱！");
            int decrease = accountMapper.decrease(userId, money);
            log.info("------->account-com.zmm.service 从账户余额中扣钱完成");
            return decrease;
        } else {
            log.info("账户余额不足，开始回滚！");
            throw new RuntimeException("账户余额不足！");
        }
    }
}
