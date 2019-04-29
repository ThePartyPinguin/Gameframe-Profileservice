package profileservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private long userId;

    @Column(name = "USER_WEBSITE")
    private String website;

    @Column(name = "USER_LOCATION")
    private String location;

    @Column(name = "USER_BIO")
    private String bio;

    @Column(name = "USER_PROFILE_PICTURE")
    private String profilePicture;

    public UserProfile() {
    }

    public UserProfile(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
