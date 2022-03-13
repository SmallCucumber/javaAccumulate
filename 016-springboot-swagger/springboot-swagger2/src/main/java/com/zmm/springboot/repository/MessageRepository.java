
package com.zmm.springboot.repository;


import com.zmm.springboot.model.Message;

import java.util.List;

public interface MessageRepository {

	List<Message> findAll();

	Message save(Message message);

	Message update(Message message);

	Message updateText(Message message);

	Message findMessage(Long id);

	void deleteMessage(Long id);

}
