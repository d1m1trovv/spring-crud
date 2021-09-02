package spring.sample.app.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.sample.app.entities.User;
import spring.sample.app.repositories.UserRepository;

import java.util.List;

@Service("userService")
public class UserService {
    UserRepository userRepository;

    public UserService(@Qualifier ("userRepository")UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        return userRepository.findById(id);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
