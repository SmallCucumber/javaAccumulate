package zmm.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hi")
    @SentinelResource(value="/testHello",blockHandler = "myBlockHandler")
    public String hi(){
        return "Hello,world";
    }

    public String myBlockHandler(BlockException ex){
        return "限流啦!!!";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
