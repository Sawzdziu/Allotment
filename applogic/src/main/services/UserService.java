package services;

import dto.UserDto;
import model.dao.UserRepositoryDAO;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    public UserDto getUserById(Integer id) {
        return new UserDto(userRepositoryDAO.findById(id));
    }

    public List<UserDto> getAllUsers() {
        return mapToUserDto(userRepositoryDAO.findAll());
    }

    public List<UserDto> getAllActiveUsers() {
        return mapToUserDto(userRepositoryDAO.findByActiveTrue());
    }

    private List<UserDto> mapToUserDto(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

}
