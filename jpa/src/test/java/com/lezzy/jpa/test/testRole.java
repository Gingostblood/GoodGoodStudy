package com.lezzy.jpa.test;

import com.lezzy.jpa.entity.Role;
import com.lezzy.jpa.entity.UserOfRole;
import com.lezzy.jpa.jpa.RoleJPA;
import com.lezzy.jpa.jpa.UserOfRoleJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class testRole {
    @Autowired
    private RoleJPA roleJPA;
    @Autowired
    private UserOfRoleJPA userOfRoleJPA;

    @Test
    @Transactional
    @Rollback(false)
    public void testRole() {
        Role role = new Role();
        role.setRoleName("管理员");
        UserOfRole userOfRole1 = new UserOfRole();
        UserOfRole userOfRole2 = new UserOfRole();
        userOfRole1.setUserName("张三").setRole(role);
        userOfRole2.setUserName("李四").setRole(role);
        role.getUsers().add(userOfRole1);
        role.getUsers().add(userOfRole2);
        roleJPA.saveAndFlush(role);
    }

    @Test
    public void testUserOfRole() {
        Role role = new Role();
        role.setRoleName("Java开发");
        UserOfRole userOfRole = new UserOfRole();
        userOfRole.setUserName("李大宇").setRole(role);
        //role.getUsers().add(userOfRole);
        userOfRoleJPA.saveAndFlush(userOfRole);
    }

    @Test
    public void testQueryRole() {
      /*  Optional<Role> role = roleJPA.findById(1);
        System.out.println("============================");
        Set<UserOfRole> users = role.get().getUsers();

        System.out.println(users.size());
        System.out.println(users);
        System.out.println("============================");*/

        Optional<UserOfRole> userOfRole = userOfRoleJPA.findById(1);
        Role role1 = userOfRole.get().getRole();
        //System.out.println(role1.getRoleName());
        System.out.println(userOfRole);
    }

    @Test
    public void testPageByQuery() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id"));
        Role role = new Role();
        role.setId(1);
        Page<Role> rolePage = roleJPA.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();

                return null;
            }
        }, pageable);
    }
}