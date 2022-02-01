package in.bushansirgul.springrestapi.Service;

import in.bushansirgul.springrestapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees(int pageNumber, int pageSize);

    Employee saveEmployee(Employee employee) ;

    Employee getSimpleEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);


}
