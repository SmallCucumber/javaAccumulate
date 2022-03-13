package com.zmm.springboot.service;


import com.zmm.springboot.modle.Customer;

import java.util.List;

public interface CustomersInterface {

    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);


}
