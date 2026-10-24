package com.supermarket.shoppinglist.service;
import com.supermarket.shoppinglist.domain.*;
import com.supermarket.shoppinglist.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class DashboardService {
 private final ShopperRepository shoppers; private final AttendantRepository attendants;
 private final TillRepository tills; private final ShoppingListRepository lists;
 private final PurchasedItemRepository items;
 public DashboardService(ShopperRepository s,AttendantRepository a,TillRepository t,ShoppingListRepository l,PurchasedItemRepository i){shoppers=s;attendants=a;tills=t;lists=l;items=i;}
 public Map<String,Long> summary(){
  Map<String,Long> m=new LinkedHashMap<>();
  m.put("shoppers",shoppers.count());
  m.put("attendants",attendants.count());
  m.put("freeTills",(long)tills.findFreeTills().size());
  m.put("listsToday",lists.countByShoppingDate(LocalDate.now()));
  m.put("drafted",lists.countByStatus(ListStatus.DRAFTED));
  m.put("purchasedItems",items.count());
  return m;
 }
}
