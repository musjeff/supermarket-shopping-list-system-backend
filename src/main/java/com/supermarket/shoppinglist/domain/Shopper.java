package com.supermarket.shoppinglist.domain;
import jakarta.persistence.*;
import java.util.UUID;
@Entity @Table(name="shopper")
public class Shopper {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 private String firstName;
 private String lastName;
 @Column(unique=true) private String loyaltyCard;
 private String phone;

 public UUID getId() { return this.id; }
 public void setId(UUID id) { this.id = id; }
 public String getFirstName() { return this.firstName; }
 public void setFirstName(String firstName) { this.firstName = firstName; }
 public String getLastName() { return this.lastName; }
 public void setLastName(String lastName) { this.lastName = lastName; }
 public String getLoyaltyCard() { return this.loyaltyCard; }
 public void setLoyaltyCard(String loyaltyCard) { this.loyaltyCard = loyaltyCard; }
 public String getPhone() { return this.phone; }
 public void setPhone(String phone) { this.phone = phone; }
}
