package club.codehero.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020-07-10
 */
@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = "t_type")
public class Type implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type")//放弃外键维护
    private List<Blog> blogs=new ArrayList<>();
}
