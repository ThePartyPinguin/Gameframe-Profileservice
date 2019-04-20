package profileservice.repository;

import profileservice.model.entity.UserProfile;

import java.util.Optional;

public interface IProfileDao {

    UserProfile save(UserProfile profile);

    Optional<UserProfile> findById(long userId);

    boolean existsById(long userId);


}
