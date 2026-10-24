package com.supermarket.shoppinglist.domain;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity @Table(name="shopping_list")
public class ShoppingList {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 private LocalDate shoppingDate;
 @Enumerated(EnumType.STRING) private ListStatus status;
 private String title;
 private double budget;
 @ManyToOne @JoinColumn(name="shopper_id") private Shopper shopper;
 @ManyToOne @JoinColumn(name="attendant_id") private Attendant attendant;

 public UUID getId() { return this.id; }
 public void setId(UUID id) { this.id = id; }
 public LocalDate getShoppingDate() { return this.shoppingDate; }
 public void setShoppingDate(LocalDate shoppingDate) { this.shoppingDate = shoppingDate; }
 public ListStatus getStatus() { return this.status; }
 public void setStatus(ListStatus status) { this.status = status; }
 public String getTitle() { return this.title; }
 public void setTitle(String title) { this.title = title; }
 public double getBudget() { return this.budget; }
 public void setBudget(double budget) { this.budget = budget; }
 public Shopper getShopper() { return this.shopper; }
 public void setShopper(Shopper shopper) { this.shopper = shopper; }
 public Attendant getAttendant() { return this.attendant; }
 public void setAttendant(Attendant attendant) { this.attendant = attendant; }
}
