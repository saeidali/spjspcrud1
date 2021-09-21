package com.example.spjspcrud.dao;

import com.example.spjspcrud.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDaoRepo extends JpaRepository<Address,Long> {

}
