package uracz.karol.githubapi.model;

import com.google.gson.annotations.SerializedName;

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

    public RepositoryDetails() {
    }

    public RepositoryDetails(String fullName, String description, String cloneUrl, Integer stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RepositoryDetails{" +
                "fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", stars=" + stars +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
