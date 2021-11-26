package club.codehero.controller;

import club.codehero.pojo.Comment;
import club.codehero.pojo.User;
import club.codehero.service.BlogService;
import club.codehero.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/14
 */
@Controller()
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    private Random r = new Random();

    @GetMapping("/findByBlogId/{blogId}")
    public String findByBlogId(@PathVariable Long blogId, Model model) {
        //Blog blog = blogService.findById(blogId);
        model.addAttribute("comments", commentService.findByBlogId(blogId));
        //model.addAttribute("blog",blogService.findById(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/add")
    public String add(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.findById(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());

        } else {

            comment.setAvatar("/image/avatar/" + (r.nextInt(39) + 1) + ".jpg");
        }


        commentService.save(comment);

        return "redirect:/comment/findByBlogId/" + blogId;

    }

}
