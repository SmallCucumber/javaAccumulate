package com.zmm.remote;

import com.zmm.entity.CommonResult;
import com.zmm.entity.Dept;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeptFallbackService implements DeptFeignService {

    @Override
    public CommonResult<Dept> get(int id) {
        return new CommonResult<>(500,"get限流",new Dept());
    }

    @Override
    public CommonResult<List<Dept>> list() {
        return new CommonResult<>(500,"list限流",new ArrayList<>());
    }

    @Override
    public CommonResult<String> timeOut() {
        return new CommonResult<>(500,"timeOut限流","限流");
    }
}
