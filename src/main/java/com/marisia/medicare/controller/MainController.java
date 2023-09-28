package com.marisia.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marisia.medicare.service.AppUserService;
import com.marisia.medicare.service.DrugService;

@Controller
public class MainController {
  @Autowired
  private final AppUserService userService;

  @Autowired
  private final DrugService drugService;

  public MainController(AppUserService userService, DrugService drugService) {
    this.userService = userService;
    this.drugService = drugService;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("drugs", drugService.findAllEnabled());
    return "index";
  }
}
