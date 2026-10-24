package com.supermarket.shoppinglist.repository;
import com.supermarket.shoppinglist.domain.Till;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface TillRepository extends JpaRepository<Till,UUID> {
 Optional<Till> findByTillNumber(int tillNumber);
 @Query("SELECT t FROM Till t WHERE NOT EXISTS (SELECT a FROM Attendant a WHERE a.till = t) ORDER BY t.tillNumber")
 List<Till> findFreeTills();
}
