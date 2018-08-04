package testapp.employee;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import testapp.employee.webservice.model.employee.Employee;
import testapp.employee.webservice.model.employee.GetEmployeesResponse;

public class TestClientRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ClientConfig.class);
        ctx.refresh();
        EmployeeClient clientTest = ctx.getBean(EmployeeClient.class);
        GetEmployeesResponse response = clientTest.getEmployees();
        for(Employee employee : response.getEmployee()) {
            System.out.println(new testapp.employee.model.Employee(employee).toString());
        }
    }
}
