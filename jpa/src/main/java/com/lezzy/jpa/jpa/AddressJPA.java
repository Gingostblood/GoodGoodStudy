package com.lezzy.jpa.jpa;

import com.lezzy.jpa.entity.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface AddressJPA extends JpaRepository<AddressInfo, Integer>, JpaSpecificationExecutor<AddressInfo>, Serializable {
}
