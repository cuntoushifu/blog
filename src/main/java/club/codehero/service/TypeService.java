package club.codehero.service;

import club.codehero.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
public interface TypeService {

    Type save(Type type);

    Type findById(Long id);

    Page<Type> findPage(Pageable pageable);

    Type update(Long id, Type type);

    void delete(Long id);

    Type findByName(String name);

    List<Type> findAllType();

    List<Type> findAllToTop(Integer integer);

    List<Type> findAllDesc();
}
