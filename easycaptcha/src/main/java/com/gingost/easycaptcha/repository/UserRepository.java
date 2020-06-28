package com.gingost.easycaptcha.repository;

import com.gingost.easycaptcha.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:lezzy
 * @Date:2020/6/28 11:36
 */
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    User findUserByUsername(String username);
}
