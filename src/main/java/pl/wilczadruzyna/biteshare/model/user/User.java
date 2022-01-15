package pl.wilczadruzyna.biteshare.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wilczadruzyna.biteshare.model.post.Post;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "ownedPosts" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany
    @JoinColumn(name = "author_id")
    @JsonProperty("ownedPosts")
    private Set<Post> ownedPosts;

    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "ratingHistory") // 3
    private List<Integer> ratingHistory;

    private BigDecimal ratingAverage;

    public void addRating(Integer rating) {
        ratingHistory.add(rating);

        double sum = 0;
        for (Integer rate : ratingHistory) {
            sum += Double.valueOf(rate);
        }

        ratingAverage = BigDecimal.valueOf(sum / ratingHistory.size()).setScale(2, RoundingMode.HALF_UP);
    }
}
