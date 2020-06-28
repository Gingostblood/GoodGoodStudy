package com.lezzy.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_age")
    private Integer age;
    @Column(name = "user_sex")
    private String sex;
    @Column(name = "user_email")
    private String email;
    @Column(name = "address_id")
    private Integer addressId;
}
