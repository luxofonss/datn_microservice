package com.quyennv.datn.user_service.adapter.db.postgres.entities;

import com.quyennv.datn.user_service.core.domain.entities.Address;
import com.quyennv.datn.user_service.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="addresses")
@Getter
@Setter
@Table(name="addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressData extends BaseEntity{
    private String country;
    private String province;
    private String district;
    private String ward;
    @Column(name="house_number")
    private String houseNumber;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserData user;

    public static AddressData from(Address address) {
        AddressData addressData = AddressData.builder()
                .country(address.getCountry())
                .province(address.getProvince())
                .district(address.getDistrict())
                .ward(address.getWard())
                .houseNumber(address.getHouseNumber())
                .build();

        addressData.setId(address.getId().getId());
        addressData.setCreatedAt(address.getCreatedAt());
        addressData.setUpdatedAt(address.getUpdatedAt());
        addressData.setDeletedAt(address.getDeletedAt());

        return addressData;
    }

    public Address fromThis() {
        return Address
                .builder()
                .id(Identity.from(this.getId()))
                .userId(Identity.from(this.getUser().getId()))
                .country(this.country)
                .province(this.province)
                .district(this.district)
                .ward(this.ward)
                .houseNumber(this.houseNumber)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
