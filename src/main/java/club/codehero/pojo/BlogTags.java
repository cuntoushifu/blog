package club.codehero.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
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
@Table ( name ="t_blog_tags")
public class BlogTags implements Serializable {


 	@Column(name = "blogs_id" )
	private Long blogsId;

 	@Column(name = "tags_id" )
	private Long tagsId;
}
