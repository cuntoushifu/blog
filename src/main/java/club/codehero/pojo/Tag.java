package club.codehero.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author YangYe
 * @Date 2020-07-10 
 */

@Data
@NoArgsConstructor(force = true)
@Entity
@Table ( name ="t_tag")
public class Tag implements Serializable {


    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" )
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Blog> blogs = new HashSet<>();
}
