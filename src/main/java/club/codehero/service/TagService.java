package club.codehero.service;

import club.codehero.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
public interface TagService {

    Tag save(Tag tag);

    Tag findById(Long id);

    Page<Tag> findPage(Pageable pageable);

    Tag update(Long id, Tag tag);

    void delete(Long id);

    Tag findByName(String name);

    List<Tag> findAllTag();

    Set<Tag> findByIdsStr(String idsStr);

    List<Tag> findAllToTop(Integer size);

    List<Tag> findAllDesc();


//    void saveByIdsStr(String tagIds);


}
