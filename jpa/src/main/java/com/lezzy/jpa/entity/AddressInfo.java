package com.lezzy.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_address")
@Accessors(chain = true)
@Data
public class AddressInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;
    @Column(name = "area_code")
    private String areaCode;

    private String country;

    private String province;

    private String city;

    private String area;
    @Column(name = "detail_address")
    private String detailAddress;
}
