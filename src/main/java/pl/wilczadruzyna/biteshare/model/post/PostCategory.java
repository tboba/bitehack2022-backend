package pl.wilczadruzyna.biteshare.model.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "PostCategory")
@Table(name = "post_categories")
@Getter
@Setter
@JsonIgnoreProperties({ "posts" })
public class PostCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @OneToMany
    @JoinColumn(name = "post_category_id")
    @JsonProperty("posts")
    private Set<Post> posts;
}
