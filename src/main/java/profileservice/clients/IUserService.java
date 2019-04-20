package profileservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import profileservice.model.dto.UserDto;
import profileservice.model.dto.response.user.UserUpdatedResponse;

@FeignClient(value = "gameframe-user-service")
public interface IUserService {

    @RequestMapping(value = "/internal/update", method = RequestMethod.PUT)
    UserUpdatedResponse updateUserData(@RequestBody UserDto userDto);


}
