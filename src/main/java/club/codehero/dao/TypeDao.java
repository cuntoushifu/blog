package club.codehero.dao;

import club.codehero.pojo.Type;
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
public interface TypeDao extends JpaRepository<Type, Long>, JpaSpecificationExecutor<Type> {

    Type findByName(String name);


    @Query(value = "from Type ")
    List<Type> findAllType();

    @Query(value = "SELECT type0.id AS id, type0.name AS NAME FROM t_type type0 ORDER BY (SELECT COUNT(blogs1.type_id) FROM t_blog blogs1 WHERE type0.id=blogs1.type_id) DESC LIMIT ?", nativeQuery = true)
    List<Type> findTop(Integer size);

    @Query(value = "from Type t  order by size(t.blogs) DESC ")
    List<Type> findAllDesc();


//    List<Type> findByOrderByBlogsDesc(Pageable pageable);


}
