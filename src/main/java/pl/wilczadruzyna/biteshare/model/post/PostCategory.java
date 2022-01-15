package pl.wilczadruzyna.biteshare.model.post;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "PostCategory")
@Table(name = "post_categories")
@Getter
@Setter
public class PostCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
}
