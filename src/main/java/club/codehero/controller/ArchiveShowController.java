package club.codehero.controller;

import club.codehero.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YangYe
 * @Date 2020/7/15
 */
@Controller
@RequestMapping("/archive")
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        model.addAttribute("archiveMap", blogService.findAllByArchive());
        model.addAttribute("blogCount", blogService.findCount());
        return "archives";

    }
}
