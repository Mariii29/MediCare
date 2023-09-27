package com.marisia.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marisia.medicare.service.AppUserService;

@Controller
public class MainController {
  @Autowired
  private final AppUserService userService;

  public MainController(AppUserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public ModelAndView index() {
    var mv = new ModelAndView("index");
    return mv;
  }
}
