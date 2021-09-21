package com.example.spjspcrud.controller;


import com.example.spjspcrud.model.UserEntity;
import com.example.spjspcrud.service.UserService;

import com.example.spjspcrud.utils.ValidationExceptionHandler;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    public final static List<String> professionList = Arrays.asList("Developer", "Designer", "Tester");

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/processForm", params = "add", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute("user") UserEntity user,
                             BindingResult bindingResult, Model model) {
        model.addAttribute("professionList", professionList);
        if (bindingResult.hasErrors()) {
            return "/index";
        }
        userService.add(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/processForm", params = "edit", method = RequestMethod.POST)
    public String submitEditForm(@ModelAttribute("user") UserEntity user, Model model) {
        model.addAttribute("professionList", professionList);
        userService.editUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/processForm", params = "search", method = RequestMethod.POST)
    public String submitSearchForm(Model model, UserEntity user) {
        model.addAttribute("professionList", professionList);
        Optional<UserEntity> userEntity = userService.getById(user.getId());
        if (userEntity.isPresent())
            user = userEntity.get();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/processForm", params = "delete", method = RequestMethod.POST)
    public String submitDeletForm(@ModelAttribute("user") UserEntity user, Model model) {
        model.addAttribute("professionList", professionList);
        userService.deleteUser(user.getId());
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model, UserEntity user) {
        model.addAttribute("user", user);
        model.addAttribute("professionList", professionList);
        return "index";
    }

    @RequestMapping(value = "/batchinsert", method = RequestMethod.GET)
    public String batchInsert() {
        return "batchinsert";
    }

    @PostMapping("/uploadcsv")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        //  validate file
        if (file.isEmpty() || !Objects.requireNonNull(file.getContentType()).equals("text/csv")) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                HeaderColumnNameMappingStrategy<UserEntity> strategy
                        = new HeaderColumnNameMappingStrategy<>();
                strategy.setType(UserEntity.class);
                // create csv bean reader
                CsvToBean<UserEntity> csvToBean = new CsvToBeanBuilder<UserEntity>(reader).withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true).withThrowExceptions(false)
                        .build();
                // convert `CsvToBean` object to list of users
                List<UserEntity> users = csvToBean.parse();
                List<CsvException> exceptions = csvToBean.getCapturedExceptions();
                if (exceptions.size() > 0) {
                    String message = exceptions.get(0).getMessage();
                    model.addAttribute("message", message);
                    LOGGER.error(exceptions.size() + " CsvExceptions");
                    return "batchinsert";
                }

                if (users.size() > 0) {
                    userService.saveAll(users);
                    LOGGER.info(users.size() + "users inserted");
                }

            } catch (IOException | NullPointerException ex) {
                model.addAttribute("message", "csv file problem" + ex.getMessage());
                LOGGER.error("IOException or NullPointerException occurred");
            } catch (TransactionSystemException e) {
                model.addAttribute("message", new ValidationExceptionHandler().handleConstraintViolation(e)
                        .toString().replace("[", "").replace("]", ""));
                LOGGER.error("TransactionSystemException" + e.getMessage());
            }
        }
        return "batchinsert";
    }

    @RequestMapping(value = "/getallresults", method = RequestMethod.GET)
    public String getAllResuls() {
        return "resulttable";
    }

    @GetMapping(value = "/filtersearch")
    public String getResults(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "2") Integer size,
                             @RequestParam(required = false, defaultValue = "id") String field, @RequestParam(required = false, defaultValue = "") String name, Model model) {

        Page<UserEntity> userEntitiesPage = userService.getSearchResults(name, page, size, field);
        int totalPage = userEntitiesPage.getTotalPages();
        List<UserEntity> content = userEntitiesPage.getContent();
        model.addAttribute("users", content);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("field", field);
        model.addAttribute("name", name);
        return "resulttable";
    }
}