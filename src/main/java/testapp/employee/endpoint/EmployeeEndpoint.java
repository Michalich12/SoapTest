package testapp.employee.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import testapp.employee.adapter.EmployeeAdapter;
import testapp.employee.model.Department;
import testapp.employee.service.EmployeeService;
import testapp.employee.webservice.model.employee.*;

import java.util.List;

/**
 * @author Igor on 03.08.2018.
 */
@Endpoint
public class EmployeeEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);

    private static final String NAMESPACE_URI = "testapp/employee/webservice/model/employee";

    @Autowired
    private EmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest")
    @ResponsePayload
    public GetEmployeesResponse getEmployees(@RequestPayload GetEmployeesRequest request) {
        GetEmployeesResponse result = null;
        try {
            result = new GetEmployeesResponse();
            List<Employee> list = result.getEmployee();
            for (testapp.employee.model.Employee employee : employeeService.getEmployees()) {
                list.add(new EmployeeAdapter(employee));
            }
        } catch (Exception e) {
            logger.warn("get Employees exception", e);
            result = new GetEmployeesResponse();
        }

        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesByDepartmentRequest")
    @ResponsePayload
    public GetEmployeesByDepartmentResponse getEmployeesByDepartment(@RequestPayload GetEmployeesByDepartmentRequest request) {
        GetEmployeesByDepartmentResponse result = null;

        try {
            result = new GetEmployeesByDepartmentResponse();
            List<Employee> list = result.getEmployee();
            for(testapp.employee.model.Employee employee : employeeService.getEmployeesByDepartment(request.getDepartmentId())) {
                list.add(new EmployeeAdapter(employee));
            }

        } catch (Exception e) {
            logger.warn("getEmployeesByDepartment exception", e);
            result = new GetEmployeesByDepartmentResponse();
        }

        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse result = null;

        try {
            result = new AddEmployeeResponse();
            testapp.employee.model.Employee newEmployee = new testapp.employee.model.Employee(request.getEmployee());
            int depId = newEmployee.getDepartment() != null ? newEmployee.getDepartment().getId() : 0;
            if(!employeeService.exists(newEmployee.getFirstname(), newEmployee.getLastname(), depId)) {
                testapp.employee.model.Employee savedEmployee = employeeService.saveEmployee(newEmployee);
                result.setNewId(savedEmployee.getId());
            }
        } catch (Exception e) {
            logger.warn("addEmployee exception", e);
            result = new AddEmployeeResponse();
        }

        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse result = null;

        try {
            result = new UpdateEmployeeResponse();
            Employee updatedEmployee = request.getEmployee();
            testapp.employee.model.Employee foundEmployee = employeeService.getEmployee(updatedEmployee.getId());
            if(foundEmployee != null) {
                testapp.employee.model.Employee savedEmployee = employeeService.saveEmployee(
                        new testapp.employee.model.Employee(updatedEmployee));
                result.setEmployee(new EmployeeAdapter(savedEmployee));
            }
        } catch (Exception e) {
            logger.warn("updateEmployee exception", e);
            result = new UpdateEmployeeResponse();
        }

        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeEmployeeRequest")
    @ResponsePayload
    public RemoveEmployeeResponse removeEmployee(@RequestPayload RemoveEmployeeRequest request) {
        RemoveEmployeeResponse result = new RemoveEmployeeResponse();;

        try {
            testapp.employee.model.Employee employee = employeeService.getEmployee(request.getEmployeeId());
            if(employee != null) {
                employeeService.deleteEmployee(request.getEmployeeId());
                result.setRemoved(true);
            } else {
                result.setRemoved(false);
            }
        } catch (Exception e) {
            logger.warn("removeEmployee exception", e);
            result.setRemoved(false);
        }

        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setEmployeeDepartmentRequest")
    @ResponsePayload
    public SetEmployeeDepartmentResponse setEmployeeDepartment(@RequestPayload SetEmployeeDepartmentRequest request) {
        SetEmployeeDepartmentResponse result = null;

        try {
            result = new SetEmployeeDepartmentResponse();
            testapp.employee.model.Employee employee = employeeService.getEmployee(request.getEmployeeId());
            Department department = employeeService.getDepartment(request.getDepartment().getId());
            if(employee != null && department != null) {
                employee.setDepartment(new Department(request.getDepartment()));

                result.setEmployee(new EmployeeAdapter(employeeService.saveEmployee(employee)));
            }
        } catch (Exception e) {
            logger.warn("setEmployeeDepartment exception", e);
            result = new SetEmployeeDepartmentResponse();
        }

        return result;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeEmployeeDepartmentRequest")
    @ResponsePayload
    public RemoveEmployeeDepartmentResponse removeEmployeeDepartment(@RequestPayload RemoveEmployeeDepartmentRequest request) {
        RemoveEmployeeDepartmentResponse result = null;

        try {
            result = new RemoveEmployeeDepartmentResponse();
            testapp.employee.model.Employee employee = employeeService.getEmployee(request.getEmployeeId());
            if(employee != null) {
                employee.setDepartment(null);

                result.setEmployee(new EmployeeAdapter(employeeService.saveEmployee(employee)));
            }
        } catch (Exception e) {
            logger.warn("removeEmployeeDepartment exception", e);
            result = new RemoveEmployeeDepartmentResponse();
        }

        return result;
    }


}
