package in.bushansirgul.springrestapi.model;

import in.bushansirgul.springrestapi.request.EmployeeRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity  // Entity class is a special type of class that represents the database table inside our application
@Table(name = "tbl_employee__relations_subject")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

     @JoinColumn(name = "department_id")  // (name = "..."): this name is up to you.
     @OneToOne
    private Department department;

     public Employee() { }

     public Employee(EmployeeRequest request) {
         this.name = request.getName();
     }

}
