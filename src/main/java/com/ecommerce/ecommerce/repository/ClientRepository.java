package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Order o WHERE o.client.id = :clientId")
    boolean existsByIdAndOrdersNotEmpty(@Param("clientId") Long clientId);
}
