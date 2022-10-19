package com.zmm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dept {

    private Integer deptNo;
    private String deptName;
    private String dbSource;

}
