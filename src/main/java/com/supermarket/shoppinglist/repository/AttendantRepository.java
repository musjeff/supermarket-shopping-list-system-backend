package com.supermarket.shoppinglist.repository;
import com.supermarket.shoppinglist.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;
public interface AttendantRepository extends JpaRepository<Attendant,UUID> {
 @Query("SELECT DISTINCT a FROM Attendant a JOIN a.departments d WHERE LOWER(d.name)=LOWER(:name)")
 List<Attendant> findByDepartmentName(@Param("name") String name);
 boolean existsByTill(Till till);
}
