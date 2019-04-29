package profileservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import profileservice.model.dto.request.UpdateProfileRequestDto;
import profileservice.model.dto.response.profile.FullUserProfileResponse;
import profileservice.model.dto.response.profile.UpdateProfileResponse;
import profileservice.service.UserProfileService;

@RestController
@RequestMapping("/private")
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @PutMapping("/update")
    public UpdateProfileResponse updateProfile(@RequestHeader("X-user-id") String userId, @RequestBody UpdateProfileRequestDto requestDto){

        long headerUserId = Long.parseLong(userId);

        if(headerUserId != requestDto.getUser().getUserId()){
            return new UpdateProfileResponse(401, "No valid request info");
        }

        return this.profileService.updateProfile(requestDto);
    }

    @GetMapping("/me")
    public FullUserProfileResponse getUserProfile(@RequestHeader("X-user-id") String userId){

        return this.profileService.getProfile(Long.parseLong(userId));

    }

}
