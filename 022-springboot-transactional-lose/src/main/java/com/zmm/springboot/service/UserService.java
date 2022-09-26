package com.zmm.springboot.service;

import com.zmm.springboot.mapper.UserMapper;
import com.zmm.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    /**
     * 事务失效场景
     * 1.不要用public修饰方法，不要同用final修饰方法,不要用方法static修饰
     * 2.spring事务传播机制用,propagation = Propagation.NOT_SUPPORTED
     * 3.不支持事务的数据库
     * 4.类没有被spring托管
     * 解决方案： （@Service）
     * 5.自己捕获了异常。
     * 解决方案：
     * 手动回滚事务(TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();)。在多线程提交的时候可以用这种方式
     * 回滚部分异常
     * 使用Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint(); 设置回滚点。
     * 使用TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint); 回滚到savePoint。
     * 6.抛出非受检异常。
     * 解决方案： @Transactional(rollbackFor = Exception.class)
     * 7.切面顺序导致。
     * 解决方案： 1、在切面中将异常原样抛出； 2、在切面中设置TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     * 8.方法嵌套导致事务失效
     * 解决方案： https://blog.csdn.net/licigao/article/details/125314484
     */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExceptionService exceptionServiceImpl;

    @Transactional
    public void insert(User user) throws Exception {

        this.exception(user);

        int s=1/0;
    }

    //@Transactional(rollbackFor = Exception.class)
    public void exception(User user){

        userMapper.insert(user);
    }

    /**
     * @Transactional 可以捕获运行时异常不能捕获Exception需要指定
     *
     * @param user
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert1(User user) throws Exception {

        userMapper.insert(user);

        //int s=1/0;
        throw new Exception("异常了");
        //throw new NullPointerException("异常了");
    }
}
