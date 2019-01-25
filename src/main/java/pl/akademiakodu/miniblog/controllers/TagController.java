package pl.akademiakodu.miniblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.miniblog.controllers.restcontrollers.TagRestController;
import pl.akademiakodu.miniblog.model.dtos.PostDto;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.PostComment;
import pl.akademiakodu.miniblog.model.entities.Tag;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import pl.akademiakodu.miniblog.model.repositories.TagRepository;
import pl.akademiakodu.miniblog.services.UserSessionService;

import java.util.Optional;

@Controller
public class TagController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagRestController tagRestController;
    @Autowired
    UserSessionService userSessionService;


    @PostMapping("/newTag")
    public String newTag(String tagName) {
        tagRestController.createTag(tagName);
        return "redirect:/addTag";
    }

    @GetMapping("/addTag")
    public String addTag(Model model) {

        model.addAttribute("tags",tagRestController.getAllTags());
        return "addTag";
    }
//////////////////////adding Tags for a link//////////////////////////////
    @GetMapping("/post/{postId}/addTag")
    public String postAddTag (@PathVariable Long postId, Model model){
        Optional<Post> postOptional = postRepository.findById(postId);
        if(userSessionService.getUserDto() == null && !userSessionService.getUserDto().getUserName().equals("administrator")){
            return "error";
        }
        postOptional.ifPresent(post -> {
            model.addAttribute("post", post);
            model.addAttribute("tags",tagRestController.getAllTags());
        });

        return "addTag";
    }

    @PostMapping("/post/{postId}/addTag")
    public String addTag(@RequestParam Long tagId, @RequestParam Long postId, Model model){


        if(userSessionService.getUserDto() == null || !userSessionService.getUserDto().getUserName().equals("administrator")){
            return "error";
        }
        Optional<Post> postOptional = postRepository.findById(postId);

        if(tagRepository.existsById(tagId)) {
            tagRestController.addTagToPost(tagId, postId);
            return "redirect:/post/" + postId;
        }
        model.addAttribute("message","This tag doesn't exist, please choose another one");
        return "redirect:/post/" + postId +"/addTag";

    }

    //////////////////////adding Tags for a link//////////////////////////////
    //@ResponseBody
    @PostMapping("/addTag")
    public String newTag (@RequestParam Long tagId, @RequestParam Long postId, Model model){
        if(userSessionService.getUserDto() == null || !userSessionService.getUserDto().getUserName().equals("administrator")){
            return "error";
        }

        if(tagRepository.existsById(tagId)) {
            tagRestController.addTagToPost(tagId, postId);
            return "redirect:/post/" + postId;
        }

        return "redirect:/addTag";
    }

}
