package in.bushansirgul.springrestapi.repository;

import in.bushansirgul.springrestapi.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findByDepartmentName(String name);

    @Query("FROM Employee where department.name = :name")
    List<Employee> getEmployeesByDepartmentName (String name);

}
