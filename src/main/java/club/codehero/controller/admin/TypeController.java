package club.codehero.controller.admin;


import club.codehero.pojo.Type;
import club.codehero.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Controller
@RequestMapping("/admin/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/findPage")
    public String findPage(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        model.addAttribute("page", typeService.findPage(pageable));
        return "admin/types";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    @GetMapping("/findById/{id}/input")
    public String findById(@PathVariable Long id, Model model) {
        Type type = typeService.findById(id);
        model.addAttribute("type", type);
        return "admin/type-input";
    }

    @PostMapping("/save")
    public String save(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        if (typeService.findByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "该分类已存在,不能重复添加");
        }

        if (result.hasErrors()) {
            return "admin/type-input";
        }

        Type t = typeService.save(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/type/findPage";

    }

    /**
     * 修改分类
     *
     * @param type       修改过的分类
     * @param result     错误返回
     * @param id         分类id
     * @param attributes 会话域
     * @return
     */
    @PostMapping("/save/{id}")
    public String save(@Valid Type type,
                       BindingResult result,
                       @PathVariable Long id,
                       RedirectAttributes attributes) {

        if (typeService.findByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "该分类已存在,不能重复添加");
        }

        if (result.hasErrors()) {
            return "admin/type-input";
        }

        Type t = typeService.update(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/type/findPage";

    }

    @GetMapping("/delete/{id}/input")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.delete(id);
        attributes.addFlashAttribute("message", "分类删除成功");
        return "redirect:/admin/type/findPage";
    }
}
