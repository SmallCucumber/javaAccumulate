package com.zmm.service;

import com.zmm.entity.Dept;

import java.util.List;

public interface DeptService {
    Dept get(Integer deptNo);

    List<Dept> selectAll();
}
