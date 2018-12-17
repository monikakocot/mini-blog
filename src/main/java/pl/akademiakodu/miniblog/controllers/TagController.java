package pl.akademiakodu.miniblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.miniblog.controllers.restcontrollers.TagRestController;
import pl.akademiakodu.miniblog.model.dtos.PostDto;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.Tag;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import pl.akademiakodu.miniblog.model.repositories.TagRepository;

@Controller
public class TagController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagRestController tagRestController;


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

    @ResponseBody
    @PostMapping("/addTag")
    public String newTag (@RequestParam Long tagId, @RequestParam Long postId, Model model){

        tagRestController.addTagToPost(tagId,postId);
        return "Your tag is added!";
    }
}
