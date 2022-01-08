
package com.zmm.springboot.repository;


import com.zmm.springboot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {


}
