package pl.akademiakodu.miniblog.services;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import org.springframework.data.domain.Sort;

import java.util.*;

@Service
public class SortService {

    @Autowired
    PostRepository postRepository;

    public List<Post> sortPosts(PostRepository postRepository){

        //Sort.Direction direction = Sort.Direction.DESC;
        //Sort sort = Sort.by(direction,"title");

        List<Post> sortedPosts = new ArrayList<>();
        //Iterable<Post> postIterable = postRepository.findAllByTitleContains(sort);
        Iterable<Post> postIterable = postRepository.findAll();
        for (Post post : postIterable) {
            sortedPosts.add(post);
        }
        Collections.sort(sortedPosts);
        return sortedPosts;
    }
}


