
package com.zmm.springboot.repository;

import com.zmm.springboot.modle.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
	public List<Customer> findByAddress(String address);
	public Customer findByUserName(String userName);
	public int  deleteByUserName(String userName);
	public Page<Customer> findByAddress(String address, Pageable pageable);

	public List<Customer> findByAgeBetween(Integer min, Integer max);
}
