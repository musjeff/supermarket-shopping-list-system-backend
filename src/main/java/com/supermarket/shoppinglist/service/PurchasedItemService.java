package com.supermarket.shoppinglist.service;
import com.supermarket.shoppinglist.domain.*;
import com.supermarket.shoppinglist.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class PurchasedItemService {
 private final PurchasedItemRepository items; private final ShoppingListRepository lists;
 public PurchasedItemService(PurchasedItemRepository i,ShoppingListRepository l){items=i;lists=l;}
 public String save(PurchasedItem item,UUID listId){
  Optional<ShoppingList> opt=lists.findById(listId);
  if(opt.isEmpty())return "The shopping list with that id does not exist";
  if(opt.get().getStatus()!=ListStatus.FULFILLED)return "Purchased items are only for FULFILLED shopping lists";
  item.setShoppingList(opt.get()); item.setPurchaseDate(LocalDate.now()); items.save(item);
  return "Purchased item saved successfully";
 }
 public List<PurchasedItem> history(String card){return items.findHistory(card);}
 public String delete(UUID id){
  Optional<PurchasedItem> i=items.findById(id); if(i.isEmpty())return "The purchased item with that id does not exist";
  items.delete(i.get()); return "Purchased item deleted successfully";
 }
}
