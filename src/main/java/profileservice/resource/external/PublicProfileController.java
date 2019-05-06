package profileservice.resource.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import profileservice.model.dto.response.profile.FullUserProfileResponse;
import profileservice.model.entity.UserProfile;
import profileservice.service.UserProfileService;

@RestController
@RequestMapping("/public")
public class PublicProfileController {

    @Autowired
    private UserProfileService profileService;

    @GetMapping("/{username}")
    public FullUserProfileResponse getPublicUserProfile(@PathVariable String username){
        return this.profileService.getPublicProfile(username);
    }


}
