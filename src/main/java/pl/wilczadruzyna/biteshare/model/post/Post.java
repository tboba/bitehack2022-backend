package pl.wilczadruzyna.biteshare.model.post;

import lombok.*;

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

    // TODO: author, location from Google API
}
