package com.supermarket.shoppinglist.repository;
import com.supermarket.shoppinglist.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.*;

public interface ShoppingListRepository extends JpaRepository<ShoppingList,UUID> {
 List<ShoppingList> findByStatusOrderByShoppingDateAsc(ListStatus status);
 List<ShoppingList> findByShoppingDateBetweenOrderByShoppingDateAsc(LocalDate start, LocalDate end);
 long countByShoppingDate(LocalDate shoppingDate);
 long countByStatus(ListStatus status);
 boolean existsByAttendantAndShoppingDateAndStatusNot(Attendant attendant, LocalDate shoppingDate, ListStatus status);
 @Query("SELECT l.status, COUNT(l) FROM ShoppingList l GROUP BY l.status")
 List<Object[]> countListsByStatus();
 @Modifying
 @Query("UPDATE ShoppingList l SET l.status=com.supermarket.shoppinglist.domain.ListStatus.ABANDONED WHERE l.attendant.id=:attendantId AND l.shoppingDate=:date AND l.status<>com.supermarket.shoppinglist.domain.ListStatus.FULFILLED")
 int cancelDay(@Param("attendantId") UUID attendantId,@Param("date") LocalDate date);
}
