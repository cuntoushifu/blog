package club.codehero.service;

import club.codehero.pojo.Comment;

import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/14
 */
public interface CommentService {

    List<Comment> findByBlogId(Long blogId);

    Comment save(Comment comment);


}
