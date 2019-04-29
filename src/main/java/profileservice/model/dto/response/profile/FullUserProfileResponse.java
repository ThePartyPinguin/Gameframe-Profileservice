package profileservice.model.dto.response.profile;

import profileservice.model.dto.UserDto;
import profileservice.model.dto.response.Response;
import profileservice.model.entity.UserProfile;

public class FullUserProfileResponse extends Response {

    private UserDto user;
    private UserProfile profile;

    public FullUserProfileResponse(int responseCode, String responseMessage, UserDto userDto, UserProfile userProfile) {
        super(responseCode, responseMessage);
        this.user = userDto;
        this.profile = userProfile;
    }

    public FullUserProfileResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
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
