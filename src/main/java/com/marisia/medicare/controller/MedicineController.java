package com.marisia.medicare.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marisia.medicare.model.Medicine;
import com.marisia.medicare.service.MedicineService;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

  private final MedicineService medicineService;

  public MedicineController(MedicineService medicineService) {
    this.medicineService = medicineService;
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping
  public ModelAndView getAllMedicines() {
    var mv = new ModelAndView();
    mv.setViewName("all");
    mv.getModel().put("medicines", medicineService.findAll());

    return mv;
  }

  @GetMapping("/{id}")
  public ModelAndView getMedicine(@PathVariable("id") Medicine medicine) {
    var mv = new ModelAndView();
    mv.setViewName("detail");
    mv.getModel().put("medicine", medicine);

    return mv;
  }
}
