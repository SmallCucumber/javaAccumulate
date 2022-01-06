package com.zmm.springboot.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailParam {
    private String userId;
    private Integer minAge;
    private Integer maxAge;
    private String realName;
    private String introduction;
    private String city;

}
