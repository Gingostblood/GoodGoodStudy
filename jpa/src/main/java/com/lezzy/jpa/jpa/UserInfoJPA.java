package com.lezzy.jpa.jpa;

import com.lezzy.jpa.dto.ViewInfo;
import com.lezzy.jpa.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface UserInfoJPA extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo>, Serializable {
    @Query(value = "select new com.lezzy.jpa.dto.ViewInfo(u,a) from  UserInfo u,AddressInfo a where u.addressId=a.id")
    List<ViewInfo> findViewInfo();


}
