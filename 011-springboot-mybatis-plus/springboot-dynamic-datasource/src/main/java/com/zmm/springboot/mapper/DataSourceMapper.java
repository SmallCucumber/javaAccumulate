package com.zmm.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmm.springboot.entity.DataSourceDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceMapper extends BaseMapper<DataSourceDTO> {
}
