package com.marisia.medicare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marisia.medicare.model.Drug;
import com.marisia.medicare.service.DrugService;

@Controller
@RequestMapping("/admin")
public class AdminController {
  private final DrugService drugService;

  public AdminController(DrugService drugService) {
    this.drugService = drugService;
  }

  @GetMapping
  public ModelAndView home() {
    var mv = new ModelAndView();
    mv.setViewName("admin/index");
    mv.getModel().put("drugs", drugService.findAll());

    return mv;
  }

  @GetMapping("/add-drug")
  public ModelAndView addDrug() {
    var mv = new ModelAndView("admin/_add_drug.html");
    mv.addObject("drug", new Drug());
    return mv;
  }

}
