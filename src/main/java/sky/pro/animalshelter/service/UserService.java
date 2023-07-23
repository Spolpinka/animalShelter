package sky.pro.animalshelter.service;

import org.springframework.stereotype.Service;
import sky.pro.animalshelter.entity.User;
import sky.pro.animalshelter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User User){
       userRepository.save(User);
    }
}


