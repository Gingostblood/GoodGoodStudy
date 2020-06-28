package com.lezzy.jpa.jpa;

import com.lezzy.jpa.entity.Role;
import com.lezzy.jpa.entity.UserOfRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserOfRoleJPA extends JpaRepository<UserOfRole,Integer>, JpaSpecificationExecutor<UserOfRole> {
}
