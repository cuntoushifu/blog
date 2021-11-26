package club.codehero.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Getter
@Setter
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
    private boolean appreciation;

    /**
     * 评论开启
     */
    @Column(name = "commentabled")
    private boolean commentabled;


    /**
     * @Lob 大字段声明
     * @Basic(fetch = FetchType.LAZY) 懒加载
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content")
    private String content;

    /**
     * 创建时间,数据库生成时间戳
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 博客描述
     */
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
    private boolean published;

    /**
     * 是否推荐
     */
    @Column(name = "recommend")
    private boolean recommend;

    /**
     * 转载版权声明开启
     */
    @Column(name = "share_statement")
    private boolean shareStatement;

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
    @ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();


    @Transient
    private String tagIds;

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(Set<Tag> tags) {
        if (tags != null && tags.size() != 0) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

}
