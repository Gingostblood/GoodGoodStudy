package com.lezzy.jpa.jpa;

import com.lezzy.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/***
 * JpaRepository简单数据操作接口
 * JpaSpecificationExecutor复杂查询接口
 */
public interface UserJPA extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, Serializable {
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    //findAgeById并不能指定查询的age，只能查询完整的一个对象
    User findAgeById(Long id);

    List<User> findByAgeLessThan(Integer age);

    List<User> findByAgeBetween(Integer age1, Integer age2);

    Page<User> findByAgeLessThan(Integer age, Pageable pageable);

    User findUserByName(String name);

    @Query(value = "select * from  user",nativeQuery = true)
    List<User> findAllUsers();

    @Query(value = "select u from User u where u.age=?1",nativeQuery = false)
    List<User> findUsersByAge(Integer age);

    User findTopByOrderByAgeDesc();

    User findFirstByOrderByAgeDesc();

    List<User> findTop3ByOrderByAgeDesc();

    List<User> findTop2ByAgeOrderByIdDesc(Integer age);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.name=?1 where u.id=?2",nativeQuery = false)
    int updateAgeById(String name,Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from User u where u.name=?1")
    int deleteUserByName(String name);

    void deleteById(Long id);

    User findUserById(Long id);

    void deleteUsersByIdIn(List<Long> ids);
}
