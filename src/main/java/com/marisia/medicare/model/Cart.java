package com.marisia.medicare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Cart {
  @Id
  @GeneratedValue
  Long id;

  @ManyToMany(targetEntity = Drug.class, fetch = FetchType.LAZY)
  @JoinTable(name = "cart_drugs", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "drug_id"))
  List<Drug> drugs = new ArrayList<>();

  @ManyToOne(optional = false)
  @JoinColumn(name = "owner_id", nullable = false, updatable = false)
  AppUser owner;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  Date createdAt;

  Boolean isCheckedOut = false;

  @Temporal(TemporalType.TIMESTAMP)
  Date checkedOutAt;

  @OneToOne
  @JoinColumn(name = "payment_id")
  Payment payment;

  public Cart() {
  }

  public Cart(List<Drug> drugs, AppUser owner, Boolean isCheckedOut, Date checkedOutAt) {
    this.drugs = drugs;
    this.owner = owner;
    this.isCheckedOut = isCheckedOut;
    this.checkedOutAt = checkedOutAt;
  }

  public Cart(AppUser owner) {
    this.owner = owner;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Drug> getDrugs() {
    return drugs;
  }

  public void setDrugs(List<Drug> drugs) {
    this.drugs = drugs;
  }

  public AppUser getOwner() {
    return owner;
  }

  public void setOwner(AppUser owner) {
    this.owner = owner;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getIsCheckedOut() {
    return isCheckedOut;
  }

  public void setIsCheckedOut(Boolean isCheckedOut) {
    this.isCheckedOut = isCheckedOut;
  }

  public Date getCheckedOutAt() {
    return checkedOutAt;
  }

  public void setCheckedOutAt(Date checkedOutAt) {
    this.checkedOutAt = checkedOutAt;
  }

  public int size() {
    return drugs.size();
  }

  public void addToCart(Drug drug) {
    drugs.add(drug);
  }

}
