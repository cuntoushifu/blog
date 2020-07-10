package club.codehero.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author YangYe
 * @Date 2020-07-10
 */

@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = "t_user")
public class User implements Serializable {


    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 头像url
     */
    @Column(name = "avatar")
    private String avatar;


    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private Integer type;


    @Column(name = "username")
    private String username;

    /**
     * 创建时间,数据库生成时间戳
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Blog> blogs=new HashSet<>();
}
