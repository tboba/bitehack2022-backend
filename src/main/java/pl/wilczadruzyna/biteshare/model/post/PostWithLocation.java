package pl.wilczadruzyna.biteshare.model.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class PostWithLocation {

    private final Post post;
    private final String location;

    public static PostWithLocation of(Post post, String location) {
        return new PostWithLocation(post, location);
    }

}
