package com.ecommerce.gtech.repositories;

import com.ecommerce.gtech.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositories extends JpaRepository<Customer, Long> {
}
