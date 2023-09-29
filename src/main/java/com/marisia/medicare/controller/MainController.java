package com.marisia.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.marisia.medicare.service.AppUserService;
import com.marisia.medicare.service.CartService;
import com.marisia.medicare.service.DrugService;

@Controller
public class MainController {
  @Autowired
  final AppUserService userService;

  @Autowired
  final DrugService drugService;

  @Autowired
  final CartService cartService;

  public MainController(AppUserService userService, DrugService drugService, CartService cartService) {
    this.userService = userService;
    this.drugService = drugService;
    this.cartService = cartService;
  }

  @GetMapping("/")
  public String index(Model model) {

    model.addAttribute("drugs", drugService.findAllEnabled());
    model.addAttribute("cart", cartService.getActiveCartForUser(userService.getCurrentUser()));
    return "index";
  }

  @PostMapping("/add-to-cart/{drugId}")
  public String addToCart(Model model, @PathVariable("drugId") Long drugId) {

    var cart = cartService.getActiveCartForUser(userService.getCurrentUser());
    var drug = drugService.findById(drugId);

    if (drug != null && drug.inStock()) {
      cart.addToCart(drug);
      cart = cartService.save(cart);
    }

    model.addAttribute("cart", cart);
    return "_cart_button";
  }
}
