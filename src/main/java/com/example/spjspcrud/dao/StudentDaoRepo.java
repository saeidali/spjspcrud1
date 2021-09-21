package com.example.spjspcrud.dao;


import com.example.spjspcrud.model.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDaoRepo extends JpaRepository<StudentInfo, Long> {
    @EntityGraph(attributePaths = {"address"})
    @Query(value = "select c from StudentInfo c where c.id IN (select c.id from StudentInfo c join c.address b where b.address like %:address%)")
    Page<StudentInfo> getByAddress(@Param("address") String address, Pageable pageable);

    @SuppressWarnings("NullableProblems")
    @EntityGraph(attributePaths = {"address"})
    Optional<StudentInfo> findById(Long id);

    @EntityGraph(attributePaths = {"address"})
    @Query("select b from StudentInfo  b where b.name like LOWER(CONCAT('%',:name, '%'))")
    Page<StudentInfo> findByName(String name, Pageable pageable);
}
