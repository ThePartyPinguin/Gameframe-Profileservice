package profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import profileservice.model.entity.UserProfile;

public interface IProfileJpaImpl extends JpaRepository<UserProfile, Long>, IProfileDao {
}
