package pl.wilczadruzyna.biteshare.model.post;

import lombok.*;
import pl.wilczadruzyna.biteshare.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Post")
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    private String description;

    private LocalDate creationDate;

    private LocalDate expiryDate;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "post_category_id")
    private PostCategory postCategory;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
