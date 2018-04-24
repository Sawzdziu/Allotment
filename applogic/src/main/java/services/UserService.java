package services;

import dto.UserDto;
import model.dao.RoleRepositoryDAO;
import model.dao.UserRepositoryDAO;
import model.entity.Role;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private RoleRepositoryDAO roleRepositoryDAO;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDto getUserById(Integer id) {
        return new UserDto(userRepositoryDAO.findById(id));
    }

    public void editUser(UserDto userDto) {
        persistUser(updateUser(userDto));
    }

    public void addUser(UserDto userDto) {
        persistUser(createUser(userDto));
    }

    public List<UserDto> getAllUsers() {
        return mapToUserDto(userRepositoryDAO.findAll());
    }

    public List<UserDto> getAllActiveUsers() {
        return mapToUserDto(userRepositoryDAO.findByActiveTrue());
    }

    private User createUser(UserDto userDto){
        User user = new User();
        user.setActive(true);
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setRole(getUserRole());
        user.setPassword(encodedPassword());

        return user;
    }

    private User updateUser(UserDto userDto){
        User user = userRepositoryDAO.findById(userDto.getIdUser());
        user.setActive(true);
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());

        return user;
    }

    private List<UserDto> mapToUserDto(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    private void persistUser(User user){
        userRepositoryDAO.save(user);
    }

    private Role getUserRole(){
        return roleRepositoryDAO.getByName("USER");
    }

    private String encodedPassword(){
        return passwordEncoder().encode("test");
    }
}
