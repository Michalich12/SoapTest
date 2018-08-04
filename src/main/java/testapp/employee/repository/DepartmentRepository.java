package testapp.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import testapp.employee.model.Department;

/**
 * @author Igor on 02.08.2018.
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
