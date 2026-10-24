package com.supermarket.shoppinglist.repository;
import com.supermarket.shoppinglist.domain.PurchasedItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface PurchasedItemRepository extends JpaRepository<PurchasedItem,UUID> {
 @Query("SELECT p FROM PurchasedItem p WHERE p.shoppingList.shopper.loyaltyCard=:card ORDER BY p.purchaseDate DESC")
 List<PurchasedItem> findHistory(@Param("card") String loyaltyCard);
 boolean existsByShoppingList(com.supermarket.shoppinglist.domain.ShoppingList shoppingList);
}
