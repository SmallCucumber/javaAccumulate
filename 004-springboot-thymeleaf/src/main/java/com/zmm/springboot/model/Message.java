package com.zmm.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;

	@Column()
	@NotEmpty(message = "Text is required.")
	private String text;

	@Column()
	@NotEmpty(message = "Summary is required.")
	private String summary;

	private Calendar created = Calendar.getInstance();
}
