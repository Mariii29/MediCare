package com.marisia.medicare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Drug {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private Float price;

  /*
   * @ManyToOne(optional = false, cascade = CascadeType.ALL)
   * 
   * @JoinColumn(name = "seller_id", nullable = true)
   * private AppUser seller;
   */

  String description;
  Boolean enabled;

  public Drug() {
  }

  public Drug(String name, Float price, String description, Boolean enabled) {
    this.name = name;
    this.price = price;
    this.description = description;
    this.enabled = enabled;
  }

  public Drug(Long id, String name, Float price, String description, Boolean enabled) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.enabled = enabled;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
  /*
   * public AppUser getSeller() {
   * return seller;
   * }
   * 
   * public void setSeller(AppUser seller) {
   * this.seller = seller;
   * }
   */

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean isEnabled() {
    return enabled;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

}
