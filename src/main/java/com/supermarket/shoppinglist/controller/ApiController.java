package com.supermarket.shoppinglist.controller;
import com.supermarket.shoppinglist.domain.*;
import com.supermarket.shoppinglist.service.*;
import com.supermarket.shoppinglist.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
 private final SupermarketService svc;
 public ApiController(SupermarketService s){svc=s;}
 @PostMapping("/departments/save") public ResponseEntity<?> saveDepartment(@RequestBody Department x){svc.save(svc.departments(),x);return ResponseEntity.status(201).body("Department saved successfully");}
 @GetMapping("/departments/all") public List<Department> allDepartments(){return svc.all(svc.departments());}
 @GetMapping("/departments/{id}") public ResponseEntity<?> department(@PathVariable UUID id){return svc.find(svc.departments(),id).<ResponseEntity<?>>map(ResponseEntity::ok).orElse(ResponseEntity.status(404).body("The department with that id does not exist"));}
 @PutMapping("/departments/update/{id}") public ResponseEntity<?> updateDepartment(@PathVariable UUID id,@RequestBody Department x){
  Optional<Department> found=svc.find(svc.departments(),id); if(found.isEmpty())return ResponseEntity.status(404).body("The department with that id does not exist");
  Department old=found.get(); old.setName(x.getName()); return ResponseEntity.ok(svc.save(svc.departments(),old));
 }
 @DeleteMapping("/departments/delete/{id}") public ResponseEntity<?> deleteDepartment(@PathVariable UUID id){if(!svc.exists(svc.departments(),id))return ResponseEntity.status(404).body("The department with that id does not exist");svc.delete(svc.departments(),id);return ResponseEntity.ok("Department deleted successfully");}
 @GetMapping("/departments/unused") public List<Department> unused(){return svc.unusedDepartments();}

 @PostMapping("/tills/save") public ResponseEntity<?> saveTill(@RequestBody Till x){svc.save(svc.tills(),x);return ResponseEntity.status(201).body("Till saved successfully");}
 @GetMapping("/tills/all") public List<Till> allTills(){return svc.all(svc.tills());}
 @GetMapping("/tills/{id}") public ResponseEntity<?> till(@PathVariable UUID id){return svc.find(svc.tills(),id).<ResponseEntity<?>>map(ResponseEntity::ok).orElse(ResponseEntity.status(404).body("The till with that id does not exist"));}
 @PutMapping("/tills/update/{id}") public ResponseEntity<?> updateTill(@PathVariable UUID id,@RequestBody Till x){
  Optional<Till> found=svc.find(svc.tills(),id); if(found.isEmpty())return ResponseEntity.status(404).body("The till with that id does not exist");
  Till old=found.get(); old.setName(x.getName()); old.setTillNumber(x.getTillNumber()); return ResponseEntity.ok(svc.save(svc.tills(),old));
 }
 @DeleteMapping("/tills/delete/{id}") public ResponseEntity<?> deleteTill(@PathVariable UUID id){if(!svc.exists(svc.tills(),id))return ResponseEntity.status(404).body("The till with that id does not exist");svc.delete(svc.tills(),id);return ResponseEntity.ok("Till deleted successfully");}
 @GetMapping("/tills/free") public List<Till> freeTills(){return svc.freeTills();}

 @PostMapping("/attendants/save") public ResponseEntity<?> saveAttendant(@RequestBody Attendant x){svc.save(svc.attendants(),x);return ResponseEntity.status(201).body("Attendant saved successfully");}
 @GetMapping("/attendants/all") public List<Attendant> allAttendants(){return svc.all(svc.attendants());}
 @GetMapping("/attendants/{id}") public ResponseEntity<?> attendant(@PathVariable UUID id){return svc.find(svc.attendants(),id).<ResponseEntity<?>>map(ResponseEntity::ok).orElse(ResponseEntity.status(404).body("The attendant with that id does not exist"));}
 @PutMapping("/attendants/update/{id}") public ResponseEntity<?> updateAttendant(@PathVariable UUID id,@RequestBody Attendant x){
  Optional<Attendant> found=svc.find(svc.attendants(),id); if(found.isEmpty())return ResponseEntity.status(404).body("The attendant with that id does not exist");
  Attendant old=found.get(); old.setFirstName(x.getFirstName()); old.setLastName(x.getLastName()); old.setBadgeNumber(x.getBadgeNumber()); old.setTill(x.getTill()); old.setDepartments(x.getDepartments());
  return ResponseEntity.ok(svc.save(svc.attendants(),old));
 }
 @DeleteMapping("/attendants/delete/{id}") public ResponseEntity<?> deleteAttendant(@PathVariable UUID id){if(!svc.exists(svc.attendants(),id))return ResponseEntity.status(404).body("The attendant with that id does not exist");svc.delete(svc.attendants(),id);return ResponseEntity.ok("Attendant deleted successfully");}
 @GetMapping("/attendants/by-department") public List<Attendant> byDepartment(@RequestParam String name){return svc.attendantsByDepartment(name);}
 @PutMapping("/attendants/{attendantId}/departments/{departmentId}") public ResponseEntity<?> addDepartment(@PathVariable UUID attendantId,@PathVariable UUID departmentId){
  String m=svc.addDepartment(attendantId,departmentId);
  if(m.equals("Department added to the attendant")) return ResponseEntity.ok(m);
  if(m.equals("The attendant already has that department")) return ResponseEntity.status(409).body(m);
  return ResponseEntity.status(404).body(m);
 }
 @PatchMapping("/attendants/{attendantId}/till") public ResponseEntity<?> moveTill(@PathVariable UUID attendantId,@RequestParam int tillNumber){
  String m=svc.moveTill(attendantId,tillNumber);
  if(m.equals("Till changed successfully")) return ResponseEntity.ok(m);
  if(m.equals("That till is taken by another attendant")) return ResponseEntity.status(409).body(m);
  return ResponseEntity.status(404).body(m);
 }

 @PostMapping("/shoppers/save") public ResponseEntity<?> saveShopper(@RequestBody Shopper x){svc.save(svc.shoppers(),x);return ResponseEntity.status(201).body("Shopper saved successfully");}
 @GetMapping("/shoppers/all") public List<Shopper> allShoppers(){return svc.all(svc.shoppers());}
 @GetMapping("/shoppers/{id}") public ResponseEntity<?> shopper(@PathVariable UUID id){return svc.find(svc.shoppers(),id).<ResponseEntity<?>>map(ResponseEntity::ok).orElse(ResponseEntity.status(404).body("The shopper with that id does not exist"));}
 @PutMapping("/shoppers/update/{id}") public ResponseEntity<?> updateShopper(@PathVariable UUID id,@RequestBody Shopper x){
  Optional<Shopper> found=svc.find(svc.shoppers(),id); if(found.isEmpty())return ResponseEntity.status(404).body("The shopper with that id does not exist");
  Shopper old=found.get(); old.setFirstName(x.getFirstName()); old.setLastName(x.getLastName()); old.setLoyaltyCard(x.getLoyaltyCard()); old.setPhone(x.getPhone());
  return ResponseEntity.ok(svc.save(svc.shoppers(),old));
 }
 @DeleteMapping("/shoppers/delete/{id}") public ResponseEntity<?> deleteShopper(@PathVariable UUID id){if(!svc.exists(svc.shoppers(),id))return ResponseEntity.status(404).body("The shopper with that id does not exist");svc.delete(svc.shoppers(),id);return ResponseEntity.ok("Shopper deleted successfully");}
 @GetMapping("/shoppers/by-last-name") public List<Shopper> byLastName(@RequestParam String lastName){return svc.shoppersByLastName(lastName);}
 @GetMapping("/shoppers/of-attendant/{attendantId}") public ResponseEntity<?> shoppersOfAttendant(@PathVariable UUID attendantId){if(!svc.exists(svc.attendants(),attendantId))return ResponseEntity.status(404).body("The attendant with that id does not exist");return ResponseEntity.ok(svc.shoppersOfAttendant(attendantId));}

 @PostMapping("/shopping-lists/save") public ResponseEntity<?> saveList(@RequestBody ShoppingList x){
  String m=svc.safeSaveList(x);
  if(m.equals("Shopping list saved successfully")) return ResponseEntity.status(201).body(m);
  if(m.equals("Attendant already fulfils a shopping list on that date")) return ResponseEntity.status(409).body(m);
  return ResponseEntity.status(400).body(m);
 }
 @GetMapping("/shopping-lists/all") public List<ShoppingList> allLists(){return svc.all(svc.lists());}
 @GetMapping("/shopping-lists/{id}") public ResponseEntity<?> list(@PathVariable UUID id){return svc.find(svc.lists(),id).<ResponseEntity<?>>map(ResponseEntity::ok).orElse(ResponseEntity.status(404).body("The shopping list with that id does not exist"));}
 @PutMapping("/shopping-lists/update/{id}") public ResponseEntity<?> updateList(@PathVariable UUID id,@RequestBody ShoppingList x){
  Optional<ShoppingList> found=svc.find(svc.lists(),id); if(found.isEmpty())return ResponseEntity.status(404).body("The shopping list with that id does not exist");
  ShoppingList old=found.get(); old.setShoppingDate(x.getShoppingDate()); old.setTitle(x.getTitle()); old.setBudget(x.getBudget()); old.setShopper(x.getShopper()); old.setAttendant(x.getAttendant());
  return ResponseEntity.ok(svc.save(svc.lists(),old));
 }
 @DeleteMapping("/shopping-lists/delete/{id}") public ResponseEntity<?> deleteList(@PathVariable UUID id){String m=svc.deleteList(id);if(m.equals("Shopping list deleted successfully"))return ResponseEntity.ok(m);if(m.startsWith("That shopping list"))return ResponseEntity.status(409).body(m);return ResponseEntity.status(404).body(m);}
 @GetMapping("/shopping-lists/by-status") public List<ShoppingList> byStatus(@RequestParam ListStatus status){return svc.listsByStatus(status);}
 @GetMapping("/shopping-lists/between") public List<ShoppingList> between(@RequestParam String start,@RequestParam String end){return svc.listsBetween(LocalDate.parse(start),LocalDate.parse(end));}
 @PatchMapping("/shopping-lists/{id}/status") public ResponseEntity<?> changeStatus(@PathVariable UUID id,@RequestParam ListStatus status){String m=svc.changeStatus(id,status);if(m.equals("Shopping list status updated successfully"))return ResponseEntity.ok(m);if(m.startsWith("The shopping list"))return ResponseEntity.status(404).body(m);return ResponseEntity.badRequest().body(m);}
 @GetMapping("/shopping-lists/stats/by-status") public List<Object[]> stats(){return svc.stats();}
 @PatchMapping("/shopping-lists/cancel-day") public ResponseEntity<String> cancelDay(@RequestParam UUID attendantId,@RequestParam String date){return ResponseEntity.ok(svc.cancelDay(attendantId,LocalDate.parse(date)));}
 @GetMapping("/shopping-lists/page") public Page<ShoppingList> page(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5") int size,@RequestParam(defaultValue="shoppingDate,desc") String sort){
  String[] parts=sort.split(",");
  Sort.Direction dir=(parts.length>1 && parts[1].equalsIgnoreCase("asc"))?Sort.Direction.ASC:Sort.Direction.DESC;
  Pageable pageable=PageRequest.of(page,size,Sort.by(dir,parts[0]));
  return svc.page(pageable);
 }
}
