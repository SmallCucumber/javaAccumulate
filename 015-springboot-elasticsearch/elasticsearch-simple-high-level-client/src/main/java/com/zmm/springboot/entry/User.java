package com.zmm.springboot.entry;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    private Date birthday;

    private String address;

    private String name;

    private String interests;

    private Integer age;
}
