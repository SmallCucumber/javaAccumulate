package com.zmm.springboot.service;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmm.springboot.entity.DataSourceDTO;
import com.zmm.springboot.mapper.DataSourceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class DataSourceService extends ServiceImpl<DataSourceMapper, DataSourceDTO> {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Autowired
    private DataSource dataSource;

    //@Autowired
    //private DefaultDataSourceCreator dataSourceCreator;

    @Autowired
    private DruidDataSourceCreator dataSourceCreator;

    public void add(DataSourceDTO dto) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        BeanUtils.copyProperties(dto, dataSourceProperty);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dto.getPoolName(), dataSource);
    }

    public List<DataSourceDTO> selectList() {
        QueryWrapper<DataSourceDTO> queryWrapper = new QueryWrapper();
        return dataSourceMapper.selectList(queryWrapper);
    }
}
