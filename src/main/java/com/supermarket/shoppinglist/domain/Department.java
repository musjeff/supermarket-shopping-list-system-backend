package com.supermarket.shoppinglist.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.*;

@Entity @Table(name="department")
public class Department {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 private String name;
 @ManyToMany(mappedBy="departments") @JsonIgnore private List<Attendant> attendants = new ArrayList<>();

 public UUID getId() { return this.id; }
 public void setId(UUID id) { this.id = id; }
 public String getName() { return this.name; }
 public void setName(String name) { this.name = name; }
 public List<Attendant> getAttendants() { return this.attendants; }
 public void setAttendants(List<Attendant> attendants) { this.attendants = attendants; }
}
