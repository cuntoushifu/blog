package club.codehero.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @Description 
 * @Author YangYe
 * @Date 2020-07-10 
 */

@Data
@NoArgsConstructor(force = true)
@Entity
@Table ( name ="t_comment")
public class Comment implements Serializable {


	@Id
 	@Column(name = "id" )
	private Long id;

 	@Column(name = "admin_comment" )
	private Boolean adminComment;

 	@Column(name = "avatar" )
	private String avatar;

 	@Column(name = "content" )
	private String content;

 	@Column(name = "create_time" )
	private Date createTime;

 	@Column(name = "email" )
	private String email;

 	@Column(name = "nickname" )
	private String nickname;

 	@Column(name = "blog_id" )
	private Long blogId;

 	@Column(name = "parent_comment_id" )
	private Long parentCommentId;
}
