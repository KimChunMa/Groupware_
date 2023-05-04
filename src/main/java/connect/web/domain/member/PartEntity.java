package connect.web.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table( name = "part" )
public class PartEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int part_no;

    @Column private String part_name;

}
