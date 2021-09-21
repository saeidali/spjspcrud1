package com.example.spjspcrud.dao;

import com.example.spjspcrud.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDaoRepo extends JpaRepository<UserEntity, Long> {
    @Query("select b from UserEntity b where b.name= :name")
    List<UserEntity> findByName(String name);

    @Query("select b from UserEntity  b where b.name like LOWER(CONCAT('%',:name, '%'))")
    Page<UserEntity> findByTitleContaining(@Param("name") String name, Pageable pageable);
}
