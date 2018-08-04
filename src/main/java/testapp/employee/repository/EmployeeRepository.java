package testapp.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import testapp.employee.model.Employee;

import java.util.List;

/**
 * @author Igor on 02.08.2018.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT a FROM Employee a WHERE a.department.id = :departmentId")
    List<Employee> findEmployeeByDepartment(@Param("departmentId") int departmentId);

    @Query("SELECT a FROM Employee a ORDER BY a.lastname, a.firstname")
    List<Employee> getEmployeesSortedByName();

    @Query("SELECT a FROM Employee a WHERE a.firstname = :firstname and a.lastname = :lastname and a.department.id = :departmentId")
    Employee exists(@Param("firstname") String firstname, @Param("lastname") String lastname,
                    @Param("departmentId") int departmentId);
}
