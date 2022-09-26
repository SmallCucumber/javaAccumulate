package com.zmm.springboot.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Dept {

    private Integer id;

    private String deptName;
}
