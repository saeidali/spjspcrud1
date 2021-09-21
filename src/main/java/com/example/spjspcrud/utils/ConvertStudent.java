package com.example.spjspcrud.utils;

import com.example.spjspcrud.dto.StudentDto;
import com.example.spjspcrud.model.Address;
import com.example.spjspcrud.model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class ConvertStudent {
    public StudentInfo studentDtoToStudentMapper(StudentDto studentDto) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setName(studentDto.getName());
        studentInfo.setEmail(studentDto.getEmail());
        List<Address> addressList = new ArrayList<>();
        if (!studentDto.getFirstAddress().isEmpty()) {
            Address firstAdrs = new Address();
            firstAdrs.setAddress(studentDto.getFirstAddress());
            firstAdrs.setStudentInfo(studentInfo);
            addressList.add(firstAdrs);
        }
        if (!studentDto.getSecondAddress().isEmpty()) {
            Address secondAdrs = new Address();
            secondAdrs.setAddress(studentDto.getSecondAddress());
            secondAdrs.setStudentInfo(studentInfo);
            addressList.add(secondAdrs);
        }
        studentInfo.setAddress(addressList);
        return studentInfo;
    }

    public StudentDto studentToStudentDtoMapper(StudentInfo studentInfo) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentInfo.getId());
        studentDto.setName(studentInfo.getName());
        studentDto.setEmail(studentInfo.getEmail());
        studentDto.setFirstAddress(studentInfo.getAddress().get(0).getAddress());
        if (studentInfo.getAddress().size() > 1) {
            studentDto.setSecondAddress(studentInfo.getAddress().get(1).getAddress());
        }
        return studentDto;
    }

    public StudentInfo studentDtoToStudentForEdit(StudentInfo studentInfo, StudentDto studentDto) {
        studentInfo.setEmail(studentDto.getEmail());
        studentInfo.setName(studentDto.getName());
        studentInfo.getAddress().get(0).setAddress(studentDto.getFirstAddress());
        studentInfo.getAddress().get(1).setAddress(studentDto.getSecondAddress());
        return studentInfo;
    }
}
