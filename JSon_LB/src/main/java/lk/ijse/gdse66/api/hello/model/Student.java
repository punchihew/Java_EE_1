package lk.ijse.gdse66.api.hello.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String id;
    private String name;
    private int age;
}
