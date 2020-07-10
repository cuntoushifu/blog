package club.codehero.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author YangYe
 * @Date 2020-07-10 
 */

@Data
@NoArgsConstructor(force = true)
@Entity
@Table ( name ="t_user" )
public class User implements Serializable {


    @Id
    @Column(name = "id" )
    private Long id;

    @Column(name = "avatar" )
    private String avatar;

    @Column(name = "create_time" )
    private Date createTime;

    @Column(name = "email" )
    private String email;

    @Column(name = "nickname" )
    private String nickname;

    @Column(name = "password" )
    private String password;

    @Column(name = "type" )
    private Long type;

    @Column(name = "update_time" )
    private Date updateTime;

    @Column(name = "username" )
    private String username;
}
