package com.gingost.easycaptcha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author:lezzy
 * @Date:2020/6/28 10:48
 */

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String role_name;

    @Column(name = "nick_name")
    private String nick_name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
