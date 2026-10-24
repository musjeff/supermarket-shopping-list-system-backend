package com.supermarket.shoppinglist.service;

import com.supermarket.shoppinglist.domain.*;
import com.supermarket.shoppinglist.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

@Service
public class SupermarketService {
 private final DepartmentRepository departments; private final TillRepository tills;
 private final AttendantRepository attendants; private final ShopperRepository shoppers;
 private final ShoppingListRepository lists; private final PurchasedItemRepository items;
 public SupermarketService(DepartmentRepository d,TillRepository t,AttendantRepository a,ShopperRepository s,ShoppingListRepository l,PurchasedItemRepository i){
  departments=d;tills=t;attendants=a;shoppers=s;lists=l;items=i;
 }
 public DepartmentRepository departments(){return departments;}
 public TillRepository tills(){return tills;}
 public AttendantRepository attendants(){return attendants;}
 public ShopperRepository shoppers(){return shoppers;}
 public ShoppingListRepository lists(){return lists;}
 public <T> T save(JpaRepository<T,UUID> repo,T entity){return repo.save(entity);}
 public <T> List<T> all(JpaRepository<T,UUID> repo){return repo.findAll();}
 public <T> Optional<T> find(JpaRepository<T,UUID> repo,UUID id){return repo.findById(id);}
 public <T> boolean exists(JpaRepository<T,UUID> repo,UUID id){return repo.existsById(id);}
 public <T> void delete(JpaRepository<T,UUID> repo,UUID id){repo.deleteById(id);}

 public List<Shopper> shoppersByLastName(String n){return shoppers.findByLastNameIgnoreCaseOrderByFirstNameAsc(n);}
 public List<ShoppingList> listsByStatus(ListStatus s){return lists.findByStatusOrderByShoppingDateAsc(s);}
 public List<ShoppingList> listsBetween(LocalDate a,LocalDate b){return lists.findByShoppingDateBetweenOrderByShoppingDateAsc(a,b);}
 public List<Attendant> attendantsByDepartment(String n){return attendants.findByDepartmentName(n);}
 public List<Till> freeTills(){return tills.findFreeTills();}
 public List<Department> unusedDepartments(){return departments.findUnused();}
 public Optional<Attendant> attendant(UUID id){return attendants.findById(id);}
 public List<Shopper> shoppersOfAttendant(UUID id){return shoppers.findShoppersOfAttendant(id);}
 public String safeSaveList(ShoppingList x){
  if(x.getShoppingDate()==null || x.getShoppingDate().isBefore(LocalDate.now())) return "Shopping list date cannot be in the past";
  if(x.getAttendant()!=null && lists.existsByAttendantAndShoppingDateAndStatusNot(x.getAttendant(),x.getShoppingDate(),ListStatus.ABANDONED))
    return "Attendant already fulfils a shopping list on that date";
  x.setStatus(ListStatus.DRAFTED); lists.save(x); return "Shopping list saved successfully";
 }
 public String changeStatus(UUID id,ListStatus next){
  Optional<ShoppingList> opt=lists.findById(id); if(opt.isEmpty()) return "The shopping list with that id does not exist";
  ShoppingList l=opt.get(); ListStatus old=l.getStatus();
  boolean ok=(old==ListStatus.DRAFTED&&(next==ListStatus.SUBMITTED||next==ListStatus.ABANDONED)) ||
    (old==ListStatus.SUBMITTED&&(next==ListStatus.FULFILLED||next==ListStatus.ABANDONED));
  if(!ok) return "Cannot change status from "+old+" to "+next;
  l.setStatus(next); lists.save(l); return "Shopping list status updated successfully";
 }
 public String addDepartment(UUID aid,UUID did){
  Optional<Attendant> a=attendants.findById(aid); if(a.isEmpty())return "The attendant with that id does not exist";
  Optional<Department> d=departments.findById(did); if(d.isEmpty())return "That department does not exist";
  if(a.get().getDepartments().contains(d.get()))return "The attendant already has that department";
  a.get().getDepartments().add(d.get()); attendants.save(a.get()); return "Department added to the attendant";
 }
 public String moveTill(UUID aid,int number){
  Optional<Attendant> a=attendants.findById(aid); if(a.isEmpty())return "The attendant with that id does not exist";
  Optional<Till> t=tills.findByTillNumber(number); if(t.isEmpty())return "The till with that tillNumber does not exist";
  if(attendants.existsByTill(t.get()) && (a.get().getTill()==null || !a.get().getTill().getId().equals(t.get().getId())))return "That till is taken by another attendant";
  a.get().setTill(t.get()); attendants.save(a.get()); return "Till changed successfully";
 }
 public String deleteList(UUID id){
  Optional<ShoppingList> l=lists.findById(id); if(l.isEmpty())return "The shopping list with that id does not exist";
  if(items.existsByShoppingList(l.get()))return "That shopping list has purchased items, delete them first";
  lists.delete(l.get()); return "Shopping list deleted successfully";
 }
 public List<Object[]> stats(){return lists.countListsByStatus();}
 @Transactional
 public String cancelDay(UUID id,LocalDate date){return lists.cancelDay(id,date)+" shopping lists cancelled";}
 public Page<ShoppingList> page(Pageable pageable){return lists.findAll(pageable);}
}
