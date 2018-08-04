package testapp.employee.adapter;

import testapp.employee.webservice.model.employee.Employee;

/**
 * @author Igor on 03.08.2018.
 */
public class EmployeeAdapter extends Employee {
    public EmployeeAdapter(testapp.employee.model.Employee employee) {
        this.setId(employee.getId());
        this.setFirstname(employee.getFirstname());
        this.setLastname(employee.getLastname());
        this.setPhone(employee.getPhone());
        this.setDepartment(new DepartmentAdapter(employee.getDepartment()));

    }
}
