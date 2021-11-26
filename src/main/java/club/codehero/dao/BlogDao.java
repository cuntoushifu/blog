package club.codehero.dao;

import club.codehero.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Repository
public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {


    @Query("from Blog where recommend= true order by updateTime desc ")
    List<Blog> findTop(Pageable pageable);

    @Query(value = "select * from t_blog where title like ?1 or content like ?1", nativeQuery = true)
    Page<Blog> findByQueryStr(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views=b.views+1 where b.id=?1")
    Integer updateViews(Long id);


    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findYearGroup();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y')=?1 order by b.updateTime desc ")
    List<Blog> findByYear(String year);


}
