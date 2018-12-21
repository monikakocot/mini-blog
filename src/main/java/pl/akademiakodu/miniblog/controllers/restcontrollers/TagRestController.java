package pl.akademiakodu.miniblog.controllers.restcontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.miniblog.model.dtos.PostDto;
import pl.akademiakodu.miniblog.model.dtos.TagDto;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.Tag;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import pl.akademiakodu.miniblog.model.repositories.TagRepository;

import java.util.List;
import java.util.Set;

@RestController
public class TagRestController {

    @Autowired
    TagRepository tagRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;


    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public ResponseEntity<TagDto> createTag(@RequestParam String tagName){
        Tag tag = new Tag();
        tag.setTagName(tagName);

        tagRepository.save(tag);

        TagDto tagDto = modelMapper.map(tag, TagDto.class);

        return ResponseEntity
                .ok()
                .body(tagDto);
    }

    @PutMapping("/tag/addToPost")
    public ResponseEntity<PostDto> addTagToPost(@RequestParam Long tagId, @RequestParam Long postId){
        Tag tag = tagRepository.getOne(tagId);
        Post post = postRepository.getOne(postId);

        post.getTags().add(tag);
        postRepository.save(post);

        return ResponseEntity.ok().body(modelMapper.map(post, PostDto.class));
    }

    @GetMapping("/tags/")
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
