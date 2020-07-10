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
@Table(name = "t_blog")
public class Blog implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 赞赏开启
     */
    @Column(name = "appreciation")
    private Boolean appreciation;

    /**
     * 评论开启
     */
    @Column(name = "commentabled")
    private Boolean commentabled;

    @Column(name = "content")
    private String content;

    /**
     * 创建时间,数据库生成时间戳
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "first_picture")
    private String firstPicture;

    /**
     * 原创 转载 翻译
     */
    @Column(name = "flag")
    private String flag;

    /**
     * 是否发布
     */
    @Column(name = "published")
    private Boolean published;

    /**
     * 是否推荐
     */
    @Column(name = "recommend")
    private Boolean recommend;

    /**
     * 转载版权声明开启
     */
    @Column(name = "share_statement")
    private Boolean shareStatement;

    @Column(name = "title")
    private String title;

    /**
     * 浏览次数
     */
    @Column(name = "views")
    private Long views;



    @ManyToOne(targetEntity = Type.class)
    private Type type;

    /**
     * 级联新增
     */
    @ManyToMany(targetEntity = Tag.class,cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToMany(mappedBy = "blog",fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

}
