package club.codehero.controller;

import club.codehero.exception.BlogNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CodeHero
 */
@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name){
//        int i=1/0;
//        String blog=null;
//        if (blog == null){
//            throw  new BlogNotFoundException("博客不存在");
//        }
        System.out.println("-------index--------");

        return "index";
    }
}
