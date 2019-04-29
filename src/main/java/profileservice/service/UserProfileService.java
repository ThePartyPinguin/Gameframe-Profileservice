package profileservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import profileservice.clients.IUserService;
import profileservice.model.dto.UserDto;
import profileservice.model.dto.request.UpdateProfileRequestDto;
import profileservice.model.dto.response.profile.FullUserProfileResponse;
import profileservice.model.dto.response.profile.UpdateProfileResponse;
import profileservice.model.dto.response.user.FullUserResponse;
import profileservice.model.dto.response.user.UserUpdatedResponse;
import profileservice.model.entity.UserProfile;
import profileservice.repository.IProfileDao;

import javax.swing.text.html.Option;
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

    public FullUserProfileResponse getProfile(long userId){

        FullUserResponse userResponse = this.userService.getUser(userId);

        if(userResponse.getResponseCode() == 501)
            return new FullUserProfileResponse(userResponse.getResponseCode(), userResponse.getResponseMessage());

        Optional<UserProfile> profileData = this.profileDao.findById(userId);

        UserProfile userProfile = null;

        if(!profileData.isPresent()){
            userProfile = new UserProfile(userId);
            this.profileDao.save(userProfile);

            return new FullUserProfileResponse(200, "User profile created", userResponse.getUserData(), userProfile);
        }
        else{
            userProfile = profileData.get();
        }

        return new FullUserProfileResponse(200, "User profile found", userResponse.getUserData(), userProfile);
    }


}
