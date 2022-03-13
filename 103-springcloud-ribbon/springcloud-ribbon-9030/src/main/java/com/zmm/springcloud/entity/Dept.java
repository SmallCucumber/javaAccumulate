package com.zmm.springcloud.entity;

import lombok.*;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-09 22:49
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dept {

    private String deptName;

    private Integer deptCount;

    private String customName;
}
