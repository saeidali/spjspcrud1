package com.example.spjspcrud.dto;

import com.example.spjspcrud.model.Address;
import com.example.spjspcrud.model.StudentInfo;
import lombok.*;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StudentDto {

    private Long id;
    @NotNull
    @Size(min = 2, max = 10, message = "{input.size.invalid}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}")
    private String name;
    @NotNull
    @Email(message = "{invalid.email}")
    private String email;
    @NotNull
    //  @NotEmpty(message = "{address.empty}")
    @Size(min = 10, max = 25, message = "{address.lenghts}")
    private String firstAddress;
    @NotNull
    @Size(max = 25, message = "{address.lenghts}")
    private String secondAddress;

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

    public List<StudentDto> studentToStudentDtoListMapper(List<StudentInfo> studentInfoList) {
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (StudentInfo studentInfo : studentInfoList) {
            studentDtoList.add(studentToStudentDtoMapper(studentInfo));
        }
        return studentDtoList;
    }

    public StudentInfo studentDtoToStudentForEdit(StudentInfo studentInfo, StudentDto studentDto) {
        studentInfo.setEmail(studentDto.getEmail());
        studentInfo.setName(studentDto.getName());
        studentInfo.getAddress().get(0).setAddress(studentDto.getFirstAddress());
        studentInfo.getAddress().get(1).setAddress(studentDto.getSecondAddress());
        return studentInfo;
    }
}
