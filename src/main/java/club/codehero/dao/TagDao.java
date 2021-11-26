package club.codehero.dao;

import club.codehero.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Repository
public interface TagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {


    Tag findByName(String name);

    @Query("from  Tag")
    List<Tag> findAllTag();

    @Query(value = "select id from t_tag", nativeQuery = true)
    List<Long> findIds();

    //List<Tag> findByOrderByBlogsDesc(Pageable pageable);

    @Query(value = "select tag0_.id as id, tag0_.name as name from t_tag tag0_ order by (select count(blogs1_.tags_id) from t_blog_tags blogs1_ where tag0_.id=blogs1_.tags_id) desc limit ? ", nativeQuery = true)
    List<Tag> findTopToSize(Integer size);

    @Query(value = "from Tag t order by size(t.blogs) DESC ")
    List<Tag> findAllDesc();
}
