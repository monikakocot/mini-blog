package pl.akademiakodu.miniblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.PostComment;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import pl.akademiakodu.miniblog.services.SortService;
import pl.akademiakodu.miniblog.services.UserSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private PostRepository postRepository;
    private UserSessionService userSessionService;


    @Autowired
    public MainController(PostRepository postRepository, UserSessionService userSessionService) {
        this.postRepository = postRepository;
        this.userSessionService = userSessionService;
    }

    @Autowired
    SortService sortService;

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("loggedUser", userSessionService.getUserDto()); //userDto store data about logged users

        if(userSessionService.getUserDto() == null) {
            model.addAttribute("name", "You are not logged in baby ;)");
            return "logout";
        }else if(userSessionService.getUserDto().getUserName().equals("administrator"))  {
            model.addAttribute("name", userSessionService.getUserDto().getUserName());
            return "admin";
        } else{
            model.addAttribute("name", userSessionService.getUserDto().getUserName());
            return "index";
        }
    }

    @GetMapping("/guest")
    public String getGuestPage(){
            return "index";
    }

    @PostMapping("/addPost")
    public String addPost(Model model, @RequestParam (value = "title") String titleParam, @RequestParam String content){
        System.out.println("Params: " + titleParam + ", " + content);
        Post post = new Post(titleParam, content);

        PostComment postComment = new PostComment();
        postComment.setComment(titleParam);

        post.addComment(postComment); // every  new post will have first post with content like title of post (to not have null)
        /* All this below now we have in addComment method
        postComment.setPost(post);
        List<PostComment> commentList = new ArrayList<>();
        commentList.add(postComment);
        post.setComments(commentList);*/

        postRepository.save(post);
        return "index";
    }

    @GetMapping("/addPost")
    public String addPostPage(){
        return "addPost";
    }

    @GetMapping("/posts")
    public String postsPage(Model model){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            postsList.add(post);
        }
        model.addAttribute("posts", postsList);
        return "posts";
    }

    @GetMapping("/postsUser")
    public String postsPageUser(Model model){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            postsList.add(post);
        }
        model.addAttribute("posts", postsList);
        return "postsUser";
    }

    // DZIALA BEZ IMPLEMENTACJI METODY findAllByTitleContains(title) !!!!!!!!!!!!! :O :O :O
    @GetMapping("/posts/{title}")
    public String postsByTitle(@PathVariable String title, Model model, Post post){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAllByTitleContains(title);
        for (Post posts : postIterable) {
            postsList.add(posts);
        }
        model.addAttribute("posts", postsList);

        return "post";
    }


// Its not the best but working ....
    @GetMapping("/sortedPosts")
    public String sortedPostsByTitle(Model model){
        model.addAttribute("posts",sortService.sortPosts(postRepository));
        return "sortedPosts";
    }



// The moethod below DOES NOT WORK

    @GetMapping("/sortedPosts2")
    public String sortedPostsByTitle2(Model model){

        Sort.Direction direction = Sort.Direction.ASC;
        Sort sort = Sort.by(direction,"title");

        List<Post> postsList = postRepository
                    .findAllByTitleContains(sort);
        model.addAttribute("posts", postsList);

        return "sortedPosts";
    }

    @GetMapping("/posts/{title}/{sortField}/{sortDirection}")
    public String sortedPostsByTitle(@PathVariable String title,
                               @PathVariable String sortField,
                               @PathVariable String sortDirection,
                               Model model){

        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equals(sortDirection)){
            direction = Sort.Direction.DESC;
        }

        List<Post> postsList = postRepository
                //.findAllByTitleContains(title, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
                //.findAllByTitleContains(title, Sort.by(Sort.Direction.fromString(sortDirection)));
                //.findAllByTitleContains(title, Sort.by(Sort.Direction.fromString(direction.toString()), sortField));
                .findAllByTitleContains(title, Sort.by(direction,sortField));
                // below we use direction variable
        model.addAttribute("posts", postsList);
        return "posts";
    }

}
