package profileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import profileservice.clients.IUserService;
import profileservice.model.dto.request.UpdateProfileRequestDto;
import profileservice.model.dto.response.profile.UpdateProfileResponse;
import profileservice.model.dto.response.user.UserUpdatedResponse;
import profileservice.model.entity.UserProfile;
import profileservice.repository.IProfileDao;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private IProfileDao profileDao;

    @Autowired
    private IUserService userService;

    public UpdateProfileResponse updateProfile(UpdateProfileRequestDto requestDto){

        UserUpdatedResponse response = this.userService.updateUserData(requestDto.getUser());

        if(!response.isUserUpdated())
        {
            return new UpdateProfileResponse(response.getResponseCode(), response.getResponseMessage());
        }

        Optional<UserProfile> data = this.profileDao.findById(requestDto.getProfile().getUserId());

        if(!data.isPresent() && !response.isUserUpdated()){
            return new UpdateProfileResponse(404, "User profile not found");
        }

        //create user profile if the user does exist the profile doesn't, to be save to support older accounts
        if(response.isUserUpdated() && !data.isPresent()){

            UserProfile updatedProfile = this.profileDao.save(requestDto.getProfile());

            return new UpdateProfileResponse(500, "Profile created", response.getUserDto(), updatedProfile);

        }

        UserProfile updatedProfile = this.profileDao.save(requestDto.getProfile());

        return new UpdateProfileResponse(500, "Profile updated", response.getUserDto(), updatedProfile);
    }


}
