package pl.wilczadruzyna.biteshare.model.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@JsonIgnoreProperties({ "posts" })
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private String city;

    @OneToMany
    @JoinColumn(name = "destination_id")
    @JsonProperty("posts")
    private Set<Post> posts;

}
