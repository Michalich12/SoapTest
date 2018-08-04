package testapp.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testapp.employee.model.Department;
import testapp.employee.model.Employee;
import testapp.employee.repository.DepartmentRepository;
import testapp.employee.repository.EmployeeRepository;
import testapp.employee.service.EmployeeService;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeRepository.findEmployeeByDepartment(departmentId);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployeesSortedByName();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.delete(id);
    }

    @Override
    public boolean exists(String firstname, String lastname, int departmentId) {
        return employeeRepository.exists(firstname, lastname, departmentId) != null;
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findOne(id);
    }
}
