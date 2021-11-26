package club.codehero.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author YangYe
 * @Date 2020-07-10
 */

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "t_tag")
public class Tag implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "数据不合法,标签名不能为空")
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Blog> blogs = new HashSet<>();
}
