package pl.akademiakodu.miniblog.model.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    private Long id; // in fact it has not to be here
    private String title;
    private String content;
    private Long idOfUser;
    private Date created;

    @JsonManagedReference
    private Set<TagDto> tags;
/*
@JsonManagedReference i @JsonBackReference są przeznaczone do obsługi tego dwukierunkowego powiązania między polami,
jeden dla roli Rodzica, drugi dla roli Dziecka.
Are used to solve the Infinite recursion (StackOverflowError).
Aby uniknąć problemu, łączenie jest obsługiwane tak, aby adnotowana właściwość z adnotacją @JsonManagedReference była obsługiwana
normalnie (normalizowana serializowana, be specjalnej obsługi dla deserializacji), a adnotowana właściwość z adnotacją
@JsonBackReference nie jest serializowana; podczas deserializacji jej wartość jest ustawiona na instancję, która ma link "managed" (forward).

@JsonIgnore wykonuje podobne funkcje, ale wyżej wymienione adnotacje są preferowane.
 */

    //GETTERS, SETTERS, TO STRING
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(Long idOfUser) {
        this.idOfUser = idOfUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }
}
