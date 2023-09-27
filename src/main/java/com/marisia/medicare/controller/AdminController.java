package com.marisia.medicare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marisia.medicare.service.MedicineService;

@Controller
@RequestMapping("/admin")
public class AdminController {
  private final MedicineService medicineService;

  public AdminController(MedicineService medicineService) {
    this.medicineService = medicineService;
  }

  @GetMapping
  public ModelAndView home() {
    var mv = new ModelAndView();
    mv.setViewName("admin/index");
    mv.getModel().put("medicines", medicineService.findAll());

    return mv;
  }

}
