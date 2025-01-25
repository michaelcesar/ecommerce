package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByClientId(Long clientId);
}
