package testapp.employee.service;

import testapp.employee.model.Department;
import testapp.employee.model.Employee;

import java.util.List;

/**
 * @author Igor on 02.08.2018.
 */
public interface EmployeeService {
    List<Employee> getEmployeesByDepartment(int departmentId);
    List<Employee> getEmployees();
    Employee saveEmployee(Employee employee);
    void deleteEmployee(int id);
    boolean exists(String firstname, String lastname, int departmentId);
    Employee getEmployee(int id);
    Department getDepartment(int id);
}
