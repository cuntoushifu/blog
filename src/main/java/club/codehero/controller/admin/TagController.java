package club.codehero.controller.admin;

import club.codehero.pojo.Tag;
import club.codehero.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Controller
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/findPage")
    public ModelAndView findPage(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView mv = new ModelAndView();
        Page<Tag> page = tagService.findPage(pageable);
        mv.addObject("page", page);
        mv.setViewName("admin/tags");
        return mv;
    }

    @RequestMapping("/add")
    public String add(Model model) {
        //防止新增页面因找不到tag报错
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    @RequestMapping("/save")
    public String save(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (tagService.findByName(tag.getName()) != null) {
            result.rejectValue("name", "NameError", "该标签已经存在,请勿重复添加");
        }
        if (result.hasErrors()) {
            return "admin/tag-input";
        }
        tagService.save(tag);
        if (tag == null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tag/findPage";
    }

    @RequestMapping("/update/{id}")
    public String update(@Valid Tag tag, BindingResult result,
                         @PathVariable Long id,
                         RedirectAttributes attributes) {
        if (tagService.findByName(tag.getName()) != null) {
            result.rejectValue("name", "NameError", "该标签已经存在,请勿重复添加");
        }
        if (result.hasErrors()) {
            return "admin/tag-input";
        }
        Tag t = tagService.update(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/tag/findPage";
    }

    @RequestMapping("/delete/{id}/input")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.delete(id);
        attributes.addFlashAttribute("message", "标签删除成功");
        return "redirect:/admin/tag/findPage";
    }

    @RequestMapping("/findById/{id}/input")
    public String findById(@PathVariable Long id, Model model) {
        Tag tag = tagService.findById(id);
        model.addAttribute("tag", tag);
        return "admin/tag-input";
    }

}
