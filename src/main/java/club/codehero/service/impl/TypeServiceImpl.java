package club.codehero.service.impl;

import club.codehero.dao.TypeDao;
import club.codehero.exception.NotFoundException;
import club.codehero.pojo.Type;
import club.codehero.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Override
    @CacheEvict(value = "type", allEntries = true)
    public Type save(Type type) {
        return typeDao.save(type);
    }

    /**
     * getOne延迟加载
     * findOne立即加载
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "type", key = "#root.args[0]")
    public Type findById(Long id) {
        Optional<Type> type = typeDao.findById(id);
        return type.get();
    }

    @Override
    public Page<Type> findPage(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    /**
     * 修改
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    @CacheEvict(value = "type", allEntries = true)
    public Type update(Long id, Type type) {
        Type t = typeDao.findById(id).get();
        if (t == null) {
            throw new NotFoundException("分类找不到");
        }
        BeanUtils.copyProperties(type, t);
        return typeDao.save(t);
    }

    @Override
    @CacheEvict(value = "type", allEntries = true)
    public void delete(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    @Cacheable(value = "type", key = "#root.args[0]")
    public Type findByName(String name) {
        return typeDao.findByName(name);
    }

    @Override
    @Cacheable(value = "type", key = "#root.methodName")
    public List<Type> findAllType() {
        return typeDao.findAllType();
    }

    @Override
    @Cacheable(value = "type", key = "#root.args[0]")
    public List<Type> findAllToTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs");
        // Pageable pageable = PageRequest.of(0, size);


        return typeDao.findTop(size);
//        return typeDao.findByOrderByBlogsDesc(pageable);
    }

    @Override
    @Cacheable(value = "type", key = "#root.methodName")
    public List<Type> findAllDesc() {
        return typeDao.findAllDesc();
    }


}
