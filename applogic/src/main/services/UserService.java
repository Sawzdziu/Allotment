package services;

import dto.UserDto;
import model.dao.UserRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    public List<UserDto> getAllUsers(){
        return userRepositoryDAO.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }
}
