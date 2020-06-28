package com.lezzy.jpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@Accessors(chain = true)

@NamedQuery(name = "User.findUserByName",query = "select u from User u where u.name=?1")
public class User implements Serializable {
    @Id
    //strategy = GenerationType.IDENTITY表示主键自增长，
    // 不写strategy的话mysql会对应auto increment，但是这里好像不起作用
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_name")
    private String name;

    @Column(name = "t_age")
    private Integer age;

    @Column(name = "t_address")
    private String address;


}
