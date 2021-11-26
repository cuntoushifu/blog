package club.codehero.controller;

import club.codehero.entity.BlogQueryEntity;
import club.codehero.pojo.Type;
import club.codehero.service.BlogService;
import club.codehero.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/15
 */
@Controller()
@RequestMapping("/types")
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/findOne/{id}")
    public String findOne(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          @PathVariable Long id, Model model) {

        List<Type> types = typeService.findAllDesc();
        if (id == -1) {
            id = types.get(0).getId();
        }

        BlogQueryEntity bloge = new BlogQueryEntity();
        bloge.setTypeId(id);

        model.addAttribute("types", types);
        model.addAttribute("page", blogService.findPage(pageable, bloge));
        model.addAttribute("activeTypeId", id);
        return "types";
    }

    @GetMapping("/findPage")
    public String findPage(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                           Long id, Model model) {

        List<Type> types = typeService.findAllDesc();
        if (id == -1) {
            id = types.get(0).getId();
        }

        BlogQueryEntity bloge = new BlogQueryEntity();
        bloge.setTypeId(id);

        model.addAttribute("types", types);
        model.addAttribute("page", blogService.findPage(pageable, bloge));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
