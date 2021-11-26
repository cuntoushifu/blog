package club.codehero.controller;

import club.codehero.pojo.Tag;
import club.codehero.service.BlogService;
import club.codehero.service.TagService;
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
@RequestMapping("/tags")
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/findOne/{id}")
    public String findOne(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          @PathVariable Long id, Model model) {

        List<Tag> tags = tagService.findAllDesc();
        if (id == -1) id = tags.get(0).getId();


        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.findPage(pageable, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }

    @GetMapping("/findPage")
    public String findPage(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                           Long id, Model model) {


        List<Tag> tags = tagService.findAllDesc();
        if (id == -1) id = tags.get(0).getId();


        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.findPage(pageable, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }

}
