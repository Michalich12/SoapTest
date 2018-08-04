package testapp.employee;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import testapp.employee.webservice.model.employee.GetEmployeesRequest;
import testapp.employee.webservice.model.employee.GetEmployeesResponse;

public class EmployeeClient extends WebServiceGatewaySupport {
    private static final String WS_URL = "http://localhost:8080/ws/";

    public GetEmployeesResponse getEmployees() {
        GetEmployeesRequest request = new GetEmployeesRequest();
        GetEmployeesResponse response = (GetEmployeesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request,
                        new SoapActionCallback(WS_URL + "getEmployeesRequest"));
        return response;
    }
}
