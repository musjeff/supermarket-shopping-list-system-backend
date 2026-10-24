
package com.supermarket.shoppinglist.repository;
import com.supermarket.shoppinglist.domain.Department;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface DepartmentRepository extends JpaRepository<Department,UUID> {
 @Query("SELECT d FROM Department d WHERE d.attendants IS EMPTY") List<Department> findUnused();
}
