package club.codehero.dao;

import club.codehero.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/14
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    List<Comment> findAllByBlog_IdAndParentCommentNullOrderByCreateTimeDesc(Long blogId);

}
