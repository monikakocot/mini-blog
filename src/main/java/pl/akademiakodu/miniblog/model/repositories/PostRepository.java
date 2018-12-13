package pl.akademiakodu.miniblog.model.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.miniblog.model.entities.Post;

import java.awt.print.Pageable;
import java.util.List;

//exception connected with data base are converted to string exception
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTitleContains(String title);
    List<Post> findAllByTitleContains(String title, Sort sort);

    List<Post> findAllByTitleContainsOrContentContains(String likeTitle, String likeContent);
//all this methods we need to implement in class wich implements PostRepository interface


}
