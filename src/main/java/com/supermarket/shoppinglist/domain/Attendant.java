package com.supermarket.shoppinglist.domain;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="attendant")
public class Attendant {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
 private String firstName;
 private String lastName;
 private String badgeNumber;
 @ManyToOne @JoinColumn(name="till_id") private Till till;
 @ManyToMany @JoinTable(name="attendant_department", joinColumns=@JoinColumn(name="attendant_id"), inverseJoinColumns=@JoinColumn(name="department_id"))
 private List<Department> departments = new ArrayList<>();

 public UUID getId() { return this.id; }
 public void setId(UUID id) { this.id = id; }
 public String getFirstName() { return this.firstName; }
 public void setFirstName(String firstName) { this.firstName = firstName; }
 public String getLastName() { return this.lastName; }
 public void setLastName(String lastName) { this.lastName = lastName; }
 public String getBadgeNumber() { return this.badgeNumber; }
 public void setBadgeNumber(String badgeNumber) { this.badgeNumber = badgeNumber; }
 public Till getTill() { return this.till; }
 public void setTill(Till till) { this.till = till; }
 public List<Department> getDepartments() { return this.departments; }
 public void setDepartments(List<Department> departments) { this.departments = departments; }
}
