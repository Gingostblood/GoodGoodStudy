package com.lezzy.jpa.test;

import com.lezzy.jpa.entity.User;
import com.lezzy.jpa.jpa.UserJPA;
import com.lezzy.jpa.utils.JpaUtils;
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

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class testJPA {
    @Autowired
    private UserJPA userJPA;
    //@PersistenceContext
    @Autowired
    private EntityManager em;

    /* private static EntityManager em;
     private static EntityTransaction et;
     static {
          em= JpaUtils.getEntityManager();
          et=em.getTransaction();
     }*/
    @Test
    public void deleteByUser() {
        User user = new User();
        user.setId(2L);
        userJPA.delete(user);
    }

    @Test
    public void addUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName(i + "号").setAge(i).setAddress(UUID.randomUUID().toString());
            users.add(user);
        }
        userJPA.saveAll(users);
    }

    @Test
    public void deleteAll() {
        userJPA.deleteAll();
    }

    @Test
    public void testMyMethod() {
        System.out.println(userJPA.findByName("李振宇"));
    }

    @Test
    public void testMyMethod2() {
        System.out.println(userJPA.findByNameAndAge("李振宇", 12));
    }

    @Test
    public void findUserById() {
        System.out.println("=====================" + userJPA.findAgeById(14L));
    }

    @Test
    public void findByAgeLessThan() {
        List<User> users = userJPA.findByAgeLessThan(16);
        System.out.println(users);
    }

    @Test
    public void findByAgeBetween() {
        List<User> users = userJPA.findByAgeBetween(10, 13);
        System.out.println(users);
    }

    @Test
    public void testPage() {
        int page = 0, size = 3;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age"));
        //userJPA.findAll(pageable);
        Page<User> pageuser = userJPA.findByAgeLessThan(10, pageable);
        List<User> users = pageuser.getContent();
        System.out.println("页数" + pageuser.getTotalPages());
        System.out.println("记录数" + pageuser.getTotalElements());
        System.out.println(users);
    }

    @Test
    public void testPage2() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "age"));
        User user = new User();
        user.setAge(10);
        user.setName("张");
        Page<User> users = userJPA.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取条件对象
                Predicate predicate = criteriaBuilder.conjunction();

                if (user.getAge() != null) {
                    //如果没有该条件，就是查找全部然后排序分页
                    predicate.getExpressions().add(criteriaBuilder.ge(root.get("age"), user.getAge()));
                }
                if (user.getName() != null && !user.getName().equals("")) {
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + user.getName() + "%"));
                }
                return predicate;
            }
        }, pageable);
        System.out.println("记录数:" + users.getTotalElements());
        System.out.println("总页数:" + users.getTotalPages());
        System.out.println(users.getContent());
    }

    @Test
    public void testNameQuery() {
        //注释掉的方法使用的是userJPA接口中定义的方法，两者都可以实现
        //User user = userJPA.findUserByName("李振宇");
        //System.out.println(user);
        Query query = em.createNamedQuery("User.findUserByName").setParameter(1, "张三丰");
        User user = (User) query.getSingleResult();
        System.out.println(user);
        em.close();
    }

    @Test
    public void testAnnotationQuery() {
        List<User> users = userJPA.findAllUsers();
        System.out.println(users);
    }

    //错误示范，不管
    @Test
    public void testJPQL() {
        /*String jpql="from User";
        Query query = em.createNamedQuery(jpql);
        et.begin();
        List<User> users = query.getResultList();
        System.out.println(users);
        et.commit();
        em.close();*/
        String jpql = "from User";
        Query query = em.createNamedQuery(jpql);
        List<User> users = query.getResultList();
        System.out.println(users);
        em.close();

    }

    @Test
    public void fkJPA() {
        //String jpql="select u from User u where u.name=?1";
        //Query query = em.createQuery(jpql).setParameter(1, "李振宇");
        String sql = "select u from User u";
        Query query = em.createQuery(sql);
        //User user = (User) query.getSingleResult();
        List<User> users = query.getResultList();
        System.out.println(users);
        em.close();
    }

    @Test
    public void testQueryByJPQL() {
        List<User> users = userJPA.findUsersByAge(12);
        System.out.println(users);
    }

    @Test
    public void testLimitQuery() {
        //User user = userJPA.findTopByOrderByAgeDesc();
        User user = userJPA.findFirstByOrderByAgeDesc();
        //System.out.println(user);
        List<User> users = userJPA.findTop3ByOrderByAgeDesc();
        List<User> users1 = userJPA.findTop2ByAgeOrderByIdDesc(12);
        System.out.println(users1);
    }

    @Test
    public void testMpdiFying() {
        int row = userJPA.updateAgeById("为什么", 11L);
        System.out.println("受影响的行数:" + row + "行");
        User user = userJPA.findAgeById(11L);
        System.out.println(user);
    }

    @Test
    public void testMpdiFying2() {
        int row = userJPA.deleteUserByName("为什么");
        System.out.println(row);
    }

    @Test
    public void testDelete() {
        userJPA.deleteById(19L);
    }

    @Test
    public void testOptional() {
        Optional<User> byId = userJPA.findById(12L);
        ;
        User user = byId.isPresent() ? byId.get() : null;
        User userById = userJPA.findUserById(12L);
        User ageById = userJPA.findAgeById(12L);
        System.out.println(ageById);
        System.out.println(userById);
        System.out.println(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeletes() {
        List<Long> ids = new ArrayList<>();
        ids.add(12L);
        ids.add(13L);
        ids.add(14L);
        userJPA.deleteUsersByIdIn(ids);
        List<User> all = userJPA.findAll();
        System.out.println(all);
    }
}
