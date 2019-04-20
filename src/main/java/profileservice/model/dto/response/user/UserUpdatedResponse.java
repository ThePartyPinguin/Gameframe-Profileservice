package profileservice.model.dto.response.user;

import profileservice.model.dto.UserDto;
import profileservice.model.dto.response.Response;

public class UserUpdatedResponse extends Response {

    private boolean userUpdated;
    private UserDto userDto;

    public UserUpdatedResponse() {
    }

    public boolean isUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(boolean userUpdated) {
        this.userUpdated = userUpdated;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
