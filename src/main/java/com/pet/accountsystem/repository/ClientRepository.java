package com.pet.accountsystem.repository;

import com.pet.accountsystem.entity.Base;
import com.pet.accountsystem.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByBase(Base base, Pageable pageable);
    @Query(
            value = """
    SELECT *
    FROM clients c
    WHERE 
      (:name IS NULL 
       OR LOWER(CAST(c.first_name AS TEXT)) LIKE LOWER(CONCAT('%', :name, '%'))
       OR LOWER(CAST(c.last_name AS TEXT)) LIKE LOWER(CONCAT('%', :name, '%'))
       OR LOWER(CAST(c.phone_number AS TEXT)) LIKE LOWER(CONCAT('%', :name, '%'))
      )
    """,
            countQuery = """
    SELECT COUNT(*)
    FROM clients c
    WHERE 
      (:name IS NULL 
       OR LOWER(CAST(c.first_name AS TEXT)) LIKE LOWER(CONCAT('%', :name, '%'))
       OR LOWER(CAST(c.last_name AS TEXT)) LIKE LOWER(CONCAT('%', :name, '%'))
       OR LOWER(CAST(c.phone_number AS TEXT)) LIKE LOWER(CONCAT('%', :name, '%'))
      )
    """,
            nativeQuery = true
    )
    Page<Client> findAllFiltered(Pageable pageable, @Param("name") String name);


}
