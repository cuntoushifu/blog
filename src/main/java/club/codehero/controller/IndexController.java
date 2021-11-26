package club.codehero.controller;

import club.codehero.dao.BlogDao;
import club.codehero.exception.NotFoundException;
import club.codehero.pojo.Blog;
import club.codehero.pojo.Type;
import club.codehero.service.BlogService;
import club.codehero.service.TagService;
import club.codehero.service.TypeService;
import club.codehero.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author CodeHero
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogDao blogDao;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        List<Type> types = typeService.findAllToTop(6);


        model.addAttribute("page", blogService.findPage(pageable));
        model.addAttribute("types", types);
        model.addAttribute("tags", tagService.findAllToTop(10));
        model.addAttribute("recommendBlogs", blogService.findRecommendBlogTop(8));
        return "index";
    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            throw new NotFoundException("id为" + id + "的博客找不到");
        }
        blogDao.updateViews(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);

        //Markdown转html
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));


        model.addAttribute("blog", b);
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query, Model model) {

        model.addAttribute("page", blogService.findPage(query, pageable));
        model.addAttribute("query", query);
        return "search";

    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model) {
        model.addAttribute("newBlogs", blogService.findRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }
}
