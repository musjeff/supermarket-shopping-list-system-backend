package com.supermarket.shoppinglist.domain;
import jakarta.persistence.*;
import java.util.UUID;
@Entity @Table(name="till")
public class Till {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 private int tillNumber;
 private String name;

 public UUID getId() { return this.id; }
 public void setId(UUID id) { this.id = id; }
 public int getTillNumber() { return this.tillNumber; }
 public void setTillNumber(int tillNumber) { this.tillNumber = tillNumber; }
 public String getName() { return this.name; }
 public void setName(String name) { this.name = name; }
}
