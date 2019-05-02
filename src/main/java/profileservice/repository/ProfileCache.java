package profileservice.repository;

import org.springframework.web.context.annotation.ApplicationScope;
import profileservice.model.entity.UserProfile;

import java.util.Optional;

@ApplicationScope
public class ProfileCache extends DaoCache<IProfileJpaImpl, UserProfile, Long> implements IProfileDao {

    @Override
    public void init(IProfileJpaImpl jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserProfile> findById(long userId) {
        Optional<UserProfile> returnValue = this.localCache.values().stream().filter(u -> u.getUserId() == userId).findFirst();

        if(!returnValue.isPresent()){
            returnValue = this.jpaRepository.findById(userId);

            if(returnValue.isPresent()){
                UserProfile data = returnValue.get();

                saveToCache(data.getUserId(), data);
            }
        }

        return returnValue;
    }

    @Override
    public boolean existsById(long userId) {

        boolean exists = this.localCache.values().stream().anyMatch(u -> u.getUserId() == userId);

        if(!exists){
            exists = this.jpaRepository.existsById(userId);

            if(exists){
                UserProfile profile = this.jpaRepository.findById(userId).get();
                saveToCache(profile.getUserId(), profile);
            }

        }

        return exists;
    }
}
