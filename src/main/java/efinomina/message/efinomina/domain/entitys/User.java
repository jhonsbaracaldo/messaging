package efinomina.message.efinomina.domain.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@Setter
public class User {
    public User(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private int id_area;
    private int dni_user;
    private String name_user;
    private String lstname_user;
    private String eml_user;


}
