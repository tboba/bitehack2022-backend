package pl.wilczadruzyna.biteshare.model.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.wilczadruzyna.biteshare.model.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity(name = "Post")
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime expiryDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Location location;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "post_category_id")
    private PostCategory postCategory;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
