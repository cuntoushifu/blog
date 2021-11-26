package club.codehero.service;

import club.codehero.entity.BlogQueryEntity;
import club.codehero.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */

public interface BlogService {

    Blog findById(Long id);


    Page<Blog> findPage(Pageable pageable);

    /**
     * 搜索
     *
     * @param pageable 分页对象
     * @param blog
     * @return
     */
    Page<Blog> findPage(Pageable pageable, BlogQueryEntity blog);


    Blog save(Blog blog);

    Blog update(Long id, Blog blog);

    void delete(Long id);

    List<Blog> findRecommendBlogTop(Integer size);

    /**
     * 搜索
     *
     * @param query
     * @return
     */
    Page<Blog> findPage(String query, Pageable pageable);

    Page<Blog> findPage(Pageable pageable, Long tagId);

    /**
     * 归档
     */
    Map<String, List<Blog>> findAllByArchive();

    Long findCount();
}
