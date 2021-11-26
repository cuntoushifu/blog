package club.codehero.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020-07-10
 */
@NoArgsConstructor(force = true)
@Entity
@Table(name = "t_comment")
@Getter
@Setter
public class Comment implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_comment")
    private Boolean adminComment;


    /**
     * 头像url
     */
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "content")
    private String content;

    /**
     * 创建时间,数据库生成timestamp
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "email")
    private String email;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    @ManyToOne(targetEntity = Blog.class)
    private Blog blog;


    /**
     * 自关联
     */
    @ManyToOne(targetEntity = Comment.class)
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

}
