package com.example.spjspcrud.service;


import com.example.spjspcrud.model.StudentInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentInfo> getAllStudentInfo();

    Optional<StudentInfo> findById(Long id);

    StudentInfo addStudent(StudentInfo studentInfo);

    StudentInfo editStudentInfo(StudentInfo studentInfo);

    void deleteStudentById(Long id);

    Page<StudentInfo> getByAddress(String address, int page, int size);

    Page<StudentInfo> findByName(String name, int page, int size);
}
