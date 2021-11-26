package club.codehero.controller.admin;

import club.codehero.entity.BlogQueryEntity;
import club.codehero.pojo.Blog;
import club.codehero.pojo.User;
import club.codehero.service.BlogService;
import club.codehero.service.TagService;
import club.codehero.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blog/findPage";


    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;


    @Autowired
    private TagService tagService;

    @GetMapping("/findPage")
    private String findPage(
            @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
            BlogQueryEntity blog, Model model) {
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("page", blogService.findPage(pageable, blog));
        // model.addAttribute("page",blogService.findPage(pageable));
        return LIST;
    }

    @PostMapping("/search")
    private String search(
            @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
            BlogQueryEntity blog, Model model) {
        model.addAttribute("page", blogService.findPage(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/add")
    public String add(Model model) {
        addTypesTagsToModel(model);
        Blog blog = new Blog();

        model.addAttribute("blog", blog);
        return INPUT;
    }


    /**
     * blog.setType(typeService.findById(blog.getType().getId()));
     * 前端的数据为type.id,springMVC封装的blog.type只有id，没有name，所以先查询到type封装进blog
     *
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/save")
    public String save(Blog blog, RedirectAttributes attributes, HttpSession session) {


        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.findById(blog.getType().getId()));
        blog.setTags(tagService.findByIdsStr(blog.getTagIds()));

        String tagIds = blog.getTagIds();
//        tagService.saveByIdsStr(tagIds);
        Blog b;
        if (blog.getId() == null) {
            b = blogService.save(blog);
        } else {
            b = blogService.update(blog.getId(), blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message", "搞错了,重新来 !");
        } else {
            attributes.addFlashAttribute("message", "搞定了, 干的漂亮 ! ");
        }

        return REDIRECT_LIST;
    }

    @GetMapping("/update/{id}/input")
    public String update(@PathVariable Long id, Model model) {
        addTypesTagsToModel(model);
        Blog blog = blogService.findById(id);
        blog.getTags().toArray();
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT;

    }

    @GetMapping("/delete/{id}/input")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.delete(id);
        attributes.addFlashAttribute("message", "分类删除成功");
        return REDIRECT_LIST;
    }


    private void addTypesTagsToModel(Model model) {
        model.addAttribute("types", typeService.findAllType());
        model.addAttribute("tags", tagService.findAllTag());

    }


}
