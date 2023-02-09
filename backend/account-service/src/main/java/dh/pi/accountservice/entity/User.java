package dh.pi.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;

@Data
@AllArgsConstructor
public class User {

    @Id
    private Integer id;

    private String cvu;

    private String alias;
}
