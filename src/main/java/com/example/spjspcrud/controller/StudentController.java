package com.example.spjspcrud.controller;

import com.example.spjspcrud.dto.StudentDto;
import com.example.spjspcrud.model.StudentInfo;
import com.example.spjspcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class StudentController {
    @Qualifier("StudentService")
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/studentinfo")
    public String home(Model model, StudentDto studentDto) {
        model.addAttribute("studentdto", studentDto);
        return "studentinfo";
    }

    @RequestMapping(value = "/processStudentForm", params = "add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("studentdto") @Valid StudentDto studentDto
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "studentinfo";
        }
        studentService.addStudent(studentDto.studentDtoToStudentMapper(studentDto));
        return "redirect:/studentinfo";
    }

    @RequestMapping(value = "/processStudentForm", params = "search", method = RequestMethod.POST)
    public String submitEditForm(@ModelAttribute("studentdto") StudentDto studentDto, Model model, RedirectAttributes
            redirectAttributes) {
        Optional<StudentInfo> studentInfo = studentService.findById(studentDto.getId());
        if (studentInfo.isPresent()) {
            studentDto = studentDto.studentToStudentDtoMapper(studentInfo.get());
            model.addAttribute("studentdto", studentDto);
            return "studentinfo";
        } else {
            redirectAttributes.addFlashAttribute("message", "Not Found");
        }
        return "redirect:studentinfo";
    }

    @RequestMapping(value = "/processStudentForm", params = "edit", method = RequestMethod.POST)
    public String submitEditForm(@ModelAttribute("studentdto") StudentDto studentDto) {
        Optional<StudentInfo> studentInfo = studentService.findById(studentDto.getId());
        studentInfo.ifPresent(info -> studentService.editStudentInfo(studentDto.studentDtoToStudentForEdit(info, studentDto)));
        return "redirect:studentinfo";
    }

    @RequestMapping(value = "/processStudentForm", params = "delete", method = RequestMethod.POST)
    public String submitDeleteForm(@ModelAttribute("studentdto") StudentDto studentDto) {
        studentService.deleteStudentById(studentDto.getId());
        return "redirect:studentinfo";
    }

    @GetMapping(value = "/studenttable")
    public String getAll(Model model, @RequestParam(required = false) String address, @RequestParam int page
            , @RequestParam int size) {
        Page<StudentInfo> studentInfoPage = studentService.getByAddress(address, page, size);
        List<StudentDto> studentDtoList = new StudentDto().studentToStudentDtoListMapper(studentInfoPage.getContent());
        model.addAttribute("studentdtolist", studentDtoList);
        model.addAttribute("totalPage", studentInfoPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("address", address);
        return "studenttable";
    }

    @GetMapping(value = "/studenttableone")
    public String getStudentpage() {
        return "studenttable";
    }
}
