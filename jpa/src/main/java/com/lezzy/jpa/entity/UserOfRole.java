package com.lezzy.jpa.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_role")
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class UserOfRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "role_id")

    private Role role;
}
