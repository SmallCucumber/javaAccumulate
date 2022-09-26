package com.zmm.springboot.controller;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
public class MetaSpaceController {
    static class OomTest{

    }

    /**
     * 模拟MetaSpace溢出，不断生成类往元空间放，类占据的空间会超过MetaSpace指定的大小
     */
    @RequestMapping("/metaSpace")
    public void metaSpace(){
        int i = 0;
        try{
            while (true){
                i++;
                /**
                 * Enhancer允许为非接口类型创建一个java代理。Enhancer动态创建了给定类型的子类但是拦截了所有的方法，
                 * 和proxy不一样的是：不管是接口还是类它都能正常工作。
                 */
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OomTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {

                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,objects);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){
            System.out.println("i的值为：" + i);
            e.printStackTrace();
        }
    }
}
