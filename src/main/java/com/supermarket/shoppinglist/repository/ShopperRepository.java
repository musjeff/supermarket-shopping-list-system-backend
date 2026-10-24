package com.supermarket.shoppinglist.repository;
import com.supermarket.shoppinglist.domain.Shopper;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface ShopperRepository extends JpaRepository<Shopper,UUID> {
 List<Shopper> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
 @Query("SELECT DISTINCT s FROM Shopper s JOIN ShoppingList l ON l.shopper=s WHERE l.attendant.id=:attendantId")
 List<Shopper> findShoppersOfAttendant(@Param("attendantId") UUID attendantId);
}
