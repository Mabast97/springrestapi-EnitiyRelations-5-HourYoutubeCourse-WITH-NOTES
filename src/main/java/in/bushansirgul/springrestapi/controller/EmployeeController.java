package in.bushansirgul.springrestapi.controller;

import in.bushansirgul.springrestapi.Service.EmployeeService;
import in.bushansirgul.springrestapi.model.Department;
import in.bushansirgul.springrestapi.model.Employee;
import in.bushansirgul.springrestapi.repository.DepartmentRepository;
import in.bushansirgul.springrestapi.repository.EmployeeRepository;
import in.bushansirgul.springrestapi.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// By adding this @Controller annotation, this class will be responsible for handling http request.
//@Controller  instead, we can use @RestController.
@RestController  // @Controller + @ResponseBody
// @RequestMapping("/api/v1")  // if we want to change the url. like (localhost:8087/api/v1/employee2) // But in that way,
// if we add another controller, it isn't contain this until we specify it on the class. So, the alternate way is that in the
// application.properties we add (server.servlet.context-path=/api/v1).
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/employeesWithPagination")  // http://localhost:8087/employeesWithPagination?pageNumber=0&pageSize=4
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return  new ResponseEntity<List<Employee>>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeWithDb(@PathVariable("id") Long id) {  // http://localhost:8087/employees2/5
        return new ResponseEntity<Employee>(employeeService.getSimpleEmployee(id), HttpStatus.OK)  ;
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeWithDb (@PathVariable("id") Long id, @RequestBody Employee employee) { // in order to receive Employee object, we use @RequestBody
        employee.setId(id);
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employeeswithdb")  // localhost:8087/employees?id=30
    public ResponseEntity<HttpStatus> deleteEmployeesWithDb(@RequestParam Long id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);  // NO_CONTENT is used for delete .
    }




    @PostMapping("/employeesPost")
    public ResponseEntity<Employee> saveEmployeeWithDb(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Department dept = new Department();
        dept.setName(employeeRequest.getDepartment());

        dept = departmentRepository.save(dept);

        // Once we saved the department into the db, we get the departmentID, So you have to set it to the Employee object.

        Employee employee = new Employee(employeeRequest);
        employee.setDepartment(dept);

        employee = employeeRepository.save(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/employees/filter/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String name) {
        return new ResponseEntity<List<Employee>>(employeeRepository.findByDepartmentName(name), HttpStatus.OK) ;
    }

    @GetMapping("/employees/filter2/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment2(@PathVariable String name) {
        return new ResponseEntity<List<Employee>>(employeeRepository.getEmployeesByDepartmentName(name), HttpStatus.OK);
    }


}
