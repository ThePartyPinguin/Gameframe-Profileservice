package profileservice.clients;

import com.netflix.client.ClientException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gameframe-file-service")
public interface IFileService {
    @GetMapping("/internal/profilepicture/{userId}")
    String getUserProfilePictureUrl(@PathVariable String userId) throws ClientException;
}
