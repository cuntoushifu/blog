package club.codehero.service.impl;


import club.codehero.dao.TagDao;
import club.codehero.exception.NotFoundException;
import club.codehero.pojo.Tag;
import club.codehero.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11f
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Override
    public Tag save(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    @Cacheable(value = "tag", key = "#root.args[0]")
    public Tag findById(Long id) {
        return tagDao.findById(id).get();
    }

    @Override
    public Page<Tag> findPage(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    @CacheEvict(value = "tag", allEntries = true)
    public Tag update(Long id, Tag tag) {
        Tag t = tagDao.findById(id).get();
        if (t == null) {
            throw new NotFoundException("标签找不到");
        }
        BeanUtils.copyProperties(tag, t);
        return tagDao.save(t);
    }

    @Override
    @CacheEvict(value = "tag", allEntries = true)
    public void delete(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    @Cacheable(value = "tag", key = "#root.args[0]")
    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    @Cacheable(value = "tag", key = "#root.methodName")
    public List<Tag> findAllTag() {
        return tagDao.findAllTag();
    }

    @Override
    public Set<Tag> findByIdsStr(String idsStr) {
        Set<Tag> set = new HashSet<>();
        if (!"".equals(idsStr) && idsStr != null) {
            String[] arr = idsStr.split(",");
            for (String s : arr) {
                set.add(tagDao.findById(Long.parseLong(s)).get());
            }
        }
        return set;
    }

    @Override
    @Cacheable(value = "tag", key = "#root.args[0]")
    public List<Tag> findAllToTop(Integer size) {
//        PageRequest pageRequest = PageRequest.of(0, size);
//        return tagDao.findByOrderByBlogsDesc(pageRequest);

        return tagDao.findTopToSize(size);
    }

    @Override
    @Cacheable(value = "tag", key = "#root.methodName")
    public List<Tag> findAllDesc() {
        return tagDao.findAllDesc();
    }


//    @Override
//    public void saveByIdsStr(String idsStr) {
//        if (!"".equals(idsStr)&&idsStr!=null) {
//            String[] arr = idsStr.split(",");
//            List<Long> idList = tagDao.findIds();
//            for (String s : arr) {
//                long id = Long.parseLong(s);
//              if (!idList.contains(id)){
//                  tagDao.save()
//              }
//            }
//        }
//    }

    private List<Long> splitToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
