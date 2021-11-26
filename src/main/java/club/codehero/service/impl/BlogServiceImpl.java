package club.codehero.service.impl;

import club.codehero.dao.BlogDao;
import club.codehero.entity.BlogQueryEntity;
import club.codehero.exception.NotFoundException;
import club.codehero.pojo.Blog;
import club.codehero.pojo.Tag;
import club.codehero.pojo.Type;
import club.codehero.service.BlogService;
import club.codehero.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;


    @Override
    @Cacheable(value = "blog", key = "#root.args[0]")
    public Blog findById(Long id) {
        //blogDao.updateViews(id);

        return blogDao.findById(id).get();
    }

    @Override
    public Page<Blog> findPage(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Page<Blog> findPage(Pageable pageable, BlogQueryEntity blog) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    @CacheEvict(value = "blog", allEntries = true)
    public Blog save(Blog blog) {
        if (blog.getFlag() == null || "".equals(blog.getFlag())) {
            blog.setFlag("原创");
        }

        blog.setCreateTime(new Date());
        blog.setViews(0L);
        blog.setUpdateTime(new Date());

        return blogDao.save(blog);
    }

    @Override
    @CacheEvict(value = "blog", allEntries = true)
    public Blog update(Long id, Blog blog) {
        Blog b = blogDao.findById(id).get();
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }

        blog.setUpdateTime(new Date());
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogDao.save(b);
    }

    @Override
    @CacheEvict(value = "blog", allEntries = true)
    public void delete(Long id) {
        blogDao.deleteById(id);
    }

    @Override
    @Cacheable(value = "blog", key = "#root.args[0]")
    public List<Blog> findRecommendBlogTop(Integer size) {
        Pageable pageRequest = PageRequest.of(0, size);
        return blogDao.findTop(pageRequest);
    }

    @Override
    public Page<Blog> findPage(String queryStr, Pageable pageable) {
        String param = "%" + queryStr + "%";
        Specification<Blog> spec = new Specification<Blog>() {
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Predicate p1 = cb.like(root.get("title").as(String.class), param);
                Predicate p2 = cb.like(root.get("content").as(String.class), param);
                Predicate p = cb.or(p1, p2);

                cq.where(p);
                return null;
            }

        };

        return blogDao.findAll(spec, pageable);
    }

    @Override
    @Cacheable(value = "blog", key = "#root.args[1]")
    public Page<Blog> findPage(Pageable pageable, Long tagId) {

        return blogDao.findAll((Specification<Blog>) (root, query, cb) -> {

            Join<Blog, Tag> join = root.join("tags");
            return cb.equal(join.get("id"), tagId);
        }, pageable);
    }

    @Override
    @Cacheable(value = "blog", key = "#root.methodName")
    public Map<String, List<Blog>> findAllByArchive() {
        List<String> years = blogDao.findYearGroup();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogDao.findByYear(year));
        }

        return map;
    }

    @Override
    @Cacheable(value = "blog", key = "#root.methodName")
    public Long findCount() {
        return blogDao.count();
    }
}
