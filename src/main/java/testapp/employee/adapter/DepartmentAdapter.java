package testapp.employee.adapter;

import testapp.employee.webservice.model.employee.Department;

/**
 * @author Igor on 03.08.2018.
 */
public class DepartmentAdapter extends Department {
    public DepartmentAdapter(testapp.employee.model.Department dep) {
        if(dep != null) {
            this.setId(dep.getId());
            this.setName(dep.getName());
        }
    }
}
