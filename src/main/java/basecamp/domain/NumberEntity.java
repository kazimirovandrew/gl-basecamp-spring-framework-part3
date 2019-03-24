package basecamp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "number")
public class NumberEntity {

    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @Column(name = "value")
    private int number;
}
