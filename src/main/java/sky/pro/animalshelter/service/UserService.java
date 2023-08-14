package sky.pro.animalshelter.service;

import org.springframework.stereotype.Service;
import sky.pro.animalshelter.entity.User;
import sky.pro.animalshelter.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User User){
       userRepository.save(User);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getByChatId(long chatId) {
        return userRepository.findByChatId(chatId);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

}


