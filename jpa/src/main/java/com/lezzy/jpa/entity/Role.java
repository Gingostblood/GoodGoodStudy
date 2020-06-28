package com.lezzy.jpa.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_role")
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<UserOfRole> users=new HashSet<>();
}
