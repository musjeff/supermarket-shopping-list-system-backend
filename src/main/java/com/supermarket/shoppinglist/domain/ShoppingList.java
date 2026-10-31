
package com.supermarket.shoppinglist.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate shoppingDate;

    @Enumerated(EnumType.STRING)
    private ListStatus status;

    private String title;
    private double budget;

    @ManyToOne
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private Attendant attendant;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getShoppingDate() { return shoppingDate; }
    public void setShoppingDate(LocalDate shoppingDate) { this.shoppingDate = shoppingDate; }

    public ListStatus getStatus() { return status; }
    public void setStatus(ListStatus status) { this.status = status; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public Shopper getShopper() { return shopper; }
    public void setShopper(Shopper shopper) { this.shopper = shopper; }

    public Attendant getAttendant() { return attendant; }
    public void setAttendant(Attendant attendant) { this.attendant = attendant; }
}
