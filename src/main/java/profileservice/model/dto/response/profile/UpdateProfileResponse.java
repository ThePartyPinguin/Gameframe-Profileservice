package profileservice.model.dto.response.profile;

import profileservice.model.dto.UserDto;
import profileservice.model.dto.response.Response;
import profileservice.model.entity.UserProfile;

public class UpdateProfileResponse extends Response {

    private UserDto userDto;
    private UserProfile userProfile;

    public UpdateProfileResponse(int responseCode, String responseMessage, UserDto userDto, UserProfile userProfile) {
        super(responseCode, responseMessage);
        this.userDto = userDto;
        this.userProfile = userProfile;
    }

    public UpdateProfileResponse(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
