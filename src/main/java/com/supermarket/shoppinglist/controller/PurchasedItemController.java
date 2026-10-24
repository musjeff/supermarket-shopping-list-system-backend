package com.supermarket.shoppinglist.controller;
import com.supermarket.shoppinglist.domain.PurchasedItem;
import com.supermarket.shoppinglist.service.PurchasedItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/purchased-items")
public class PurchasedItemController {
 private final PurchasedItemService service;
 public PurchasedItemController(PurchasedItemService service){this.service=service;}

 @PostMapping("/save")
 public ResponseEntity<?> save(@RequestParam UUID shoppingListId,@RequestBody PurchasedItem item){
  String m=service.save(item,shoppingListId);
  if(m.equals("Purchased item saved successfully")) return ResponseEntity.status(201).body(m);
  if(m.equals("The shopping list with that id does not exist")) return ResponseEntity.status(404).body(m);
  return ResponseEntity.status(400).body(m);
 }

 @GetMapping("/shopper/{loyaltyCard}")
 public List<PurchasedItem> history(@PathVariable String loyaltyCard){return service.history(loyaltyCard);}

 @DeleteMapping("/delete/{id}")
 public ResponseEntity<?> delete(@PathVariable UUID id){
  String m=service.delete(id);
  if(m.equals("Purchased item deleted successfully")) return ResponseEntity.ok(m);
  return ResponseEntity.status(404).body(m);
 }
}
