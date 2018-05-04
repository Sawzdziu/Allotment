package services;

import dto.AddEditUserDto;
import dto.allotmentUser.UserDto;
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

    @Autowired
    private AllotmentUserService allotmentUserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDto getUserById(Integer id) {
        return new UserDto(userRepositoryDAO.findById(id));
    }

    public void editUser(AddEditUserDto addEditUserDto) {
        if (addEditUserDto.getActive()) {
            persistUser(updateUser(addEditUserDto));
        } else {
            deactivateUser(addEditUserDto);
        }
    }

    public void addUser(AddEditUserDto addEditUserDto) {
        createUser(addEditUserDto);
    }

    public List<UserDto> getAllUsers() {
        return mapToUserDto(userRepositoryDAO.findAll());
    }

    public List<UserDto> getAllActiveUsers() {
        return mapToUserDto(userRepositoryDAO.findByActiveTrue());
    }

    private void createUser(AddEditUserDto addEditUserDto) {
        User user = new User();
        user.setActive(true);
        user.setEmail(addEditUserDto.getEmail());
        user.setLastName(addEditUserDto.getLastName());
        user.setName(addEditUserDto.getName());
        user.setPhone(addEditUserDto.getPhone());
        user.setRole(getUserRole(addEditUserDto.getName()));
        user.setPassword(encodedPassword());

        persistUser(user);

        deactivateAllotmentAndUser(addEditUserDto.getAllotmentId());
        createAllotmentUser(user, addEditUserDto.getAllotmentId());

    }

    private User updateUser(AddEditUserDto addEditUserDto) {
        User user = userRepositoryDAO.findById(addEditUserDto.getIdUser());
        user.setActive(true);
        user.setEmail(addEditUserDto.getEmail());
        user.setLastName(addEditUserDto.getLastName());
        user.setName(addEditUserDto.getName());
        user.setPhone(addEditUserDto.getPhone());

        return user;
    }

    private void deactivateUser(AddEditUserDto addEditUserDto) {
        User user = userRepositoryDAO.findById(addEditUserDto.getIdUser());
        user.setActive(false);
        user.setEmail(addEditUserDto.getEmail());
        user.setLastName(addEditUserDto.getLastName());
        user.setName(addEditUserDto.getName());
        user.setPhone(addEditUserDto.getPhone());

        deactivateAllotemntUser(addEditUserDto.getAllotmentId());

        persistUser(user);
    }

    private void createAllotmentUser(User user, Integer allotmentId) {
        allotmentUserService.addNewAllotmentUser(allotmentId, user);
    }

    private void deactivateAllotemntUser(Integer idUser) {
        allotmentUserService.deactivateAllotmentUserByUserId(idUser);
    }

    private void deactivateAllotmentAndUser(Integer allotmentId) {
        allotmentUserService.deactivateAllotmentUserByAllotmentId(allotmentId);
    }

    private List<UserDto> mapToUserDto(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    private void persistUser(User user) {
        userRepositoryDAO.save(user);
    }

    private Role getUserRole(String name) {
        return roleRepositoryDAO.getByName(name);
    }

    private Role getRoleById(Integer id) {
        return roleRepositoryDAO.getRoleById(id);
    }

    private String encodedPassword() {
        return passwordEncoder().encode("test");
    }
}
