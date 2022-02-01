package in.bushansirgul.springrestapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity  // Entity class is a special type of class that represents the database table inside our application
@Table(name = "tbl_department_relations_subject") // to map the class with the table in the db.
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;




}
