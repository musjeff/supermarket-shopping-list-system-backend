package com.supermarket.shoppinglist.domain;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity @Table(name="purchased_item")
public class PurchasedItem {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 private String itemName;
 private String note;
 private int quantity;
 private LocalDate purchaseDate;
 @ManyToOne @JoinColumn(name="shopping_list_id") private ShoppingList shoppingList;

 public UUID getId() { return this.id; }
 public void setId(UUID id) { this.id = id; }
 public String getItemName() { return this.itemName; }
 public void setItemName(String itemName) { this.itemName = itemName; }
 public String getNote() { return this.note; }
 public void setNote(String note) { this.note = note; }
 public int getQuantity() { return this.quantity; }
 public void setQuantity(int quantity) { this.quantity = quantity; }
 public LocalDate getPurchaseDate() { return this.purchaseDate; }
 public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
 public ShoppingList getShoppingList() { return this.shoppingList; }
 public void setShoppingList(ShoppingList shoppingList) { this.shoppingList = shoppingList; }
}
