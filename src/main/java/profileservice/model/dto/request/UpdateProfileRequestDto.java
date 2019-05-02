package profileservice.model.dto.request;

import profileservice.model.dto.UserDto;
import profileservice.model.entity.UserProfile;

public class UpdateProfileRequestDto {

    private UserProfile profile;
    private UserDto user;

    public UpdateProfileRequestDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
