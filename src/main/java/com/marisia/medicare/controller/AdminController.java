package com.marisia.medicare.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.marisia.medicare.model.AppUser;
import com.marisia.medicare.model.Drug;
import com.marisia.medicare.model.SecurityUser;
import com.marisia.medicare.service.DrugService;

import jakarta.validation.Valid;

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

  @GetMapping("/drugs")
  public String allDrugs(Model model, @RequestHeader(value = "HX-Request", required = false) String htmxRequestHeader,
      @RequestParam(value = "_hxm", required = false) String htmxRequestParameter) {

    model.addAttribute("drugs", drugService.findAll());

    // Check if it's an HTMX request
    boolean isHtmxRequest = htmxRequestHeader != null || htmxRequestParameter != null;
    if (isHtmxRequest) {
      return "admin/_drugs_table";
    } else {
      return "admin/index";
    }
  }

  @GetMapping("/add-drug")
  public ModelAndView newDrugForm() {
    var mv = new ModelAndView("admin/_drug_form.html");
    mv.addObject("postLocation", "/admin/add-drug");
    mv.addObject("drug", new Drug());
    return mv;
  }

  @PostMapping("/add-drug")
  public String addDrug(
      Model model,
      @Valid @ModelAttribute("drug") Drug drugData,
      BindingResult result) {

    boolean hasErrors = false;

    if (drugService.drugWithNameAlreadyExists(drugData.getName())) {
      result.rejectValue("name",
          "error.drug",
          "Drug with name: " + drugData.getName() + " already exists!");
      hasErrors = true;
    }

    if (result.hasErrors()) {
      hasErrors = true;
    }

    if (hasErrors) {
      model.addAttribute("postLocation", "/admin/add-drug");
      return "admin/_drug_form";
    }

    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var user = (SecurityUser) authentication.getPrincipal();
    drugData.setSeller(user.getUser());
    drugService.addDrug(drugData);

    return "redirect:/admin/add-drug";

  }

}
