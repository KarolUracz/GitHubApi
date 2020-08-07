package uracz.karol.githubapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RepositoryDetails {
    @SerializedName("full_name")
    private String fullName;
    private String description;
    @SerializedName("clone_url")
    private String cloneUrl;
    @SerializedName("stargazers_count")
    private Integer stars;
    @SerializedName("created_at")
    private String createdAt;
}
