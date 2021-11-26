package club.codehero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

/**
 * @Author YangYe
 * @Date 2020/7/15
 */
@Controller
public class AboutController {

    private Random r = new Random();

    @GetMapping("/about")
    public String aboutMe(Model model) {
        model.addAttribute("img", "image/about/" + r.nextInt(10) + ".jpeg");

        return "about";
    }
}
