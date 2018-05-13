package services;

import dto.AddEditUserDto;
import dto.allotmentUser.UserDto;
import model.dao.RoleRepositoryDAO;
import model.dao.UserRepositoryDAO;
import model.entity.Role;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
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
        persistUser(updateUser(addEditUserDto));
    }

    public void addUser(AddEditUserDto addEditUserDto) {
        createUser(addEditUserDto);
    }

    public void deactivateUser(Integer id){
        User user = userRepositoryDAO.findById(id);
        user.setActive(false);

        deactivateAllotmentUserByUserId(id);

        persistUser(user);
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
        user.setUsername(addEditUserDto.getUsername());
        user.setEmail(addEditUserDto.getEmail());
        user.setLastName(addEditUserDto.getLastName());
        user.setName(addEditUserDto.getName());
        user.setPhone(addEditUserDto.getPhone());
        user.setRole(getUserRole(addEditUserDto.getRoleName()));
        user.setPassword(encodedPassword());

        persistUser(user);

        if (addEditUserDto.getAllotmentId() != 0) {
            deactivateAllAllotmentUserByAllotmentId(addEditUserDto.getAllotmentId());
            createAllotmentUser(user, addEditUserDto.getAllotmentId());
        }
    }

    private User updateUser(AddEditUserDto addEditUserDto) {
        User user = userRepositoryDAO.findById(addEditUserDto.getIdUser());
        user.setActive(true);
        user.setUsername(addEditUserDto.getUsername());
        user.setEmail(addEditUserDto.getEmail());
        user.setLastName(addEditUserDto.getLastName());
        user.setName(addEditUserDto.getName());
        user.setPhone(addEditUserDto.getPhone());
        user.setRole(getUserRole(addEditUserDto.getRoleName()));

        if (addEditUserDto.getAllotmentId() == 0 || addEditUserDto.getAllotmentId().equals(actualAllotment(user.getIdUser()))) {
            return user;
        } else {
            //Deactivates allotment connections which user has before
            deactivateAllotmentUserByUserId(user.getIdUser());
            //Deactivates all connections for specified allotment
            deactivateAllotmentUserByAllotmentId(addEditUserDto.getAllotmentId(), user);

            updateAllotmentUser(user, addEditUserDto.getAllotmentId());
            return user;
        }
    }

    private Integer actualAllotment(Integer idUser) {
        return allotmentUserService.getAllotmentUserAndActiveTrue(idUser).getAllotment().getIdAllotment();
    }

    private void updateAllotmentUser(User user, Integer allotmentId) {
        allotmentUserService.updateAllotmentUser(allotmentId, user);
    }

    private void createAllotmentUser(User user, Integer allotmentId) {
        allotmentUserService.addNewAllotmentUser(allotmentId, user);
    }

    private void deactivateAllotmentUserByUserId(Integer idUser) {
        allotmentUserService.deactivateAllotmentUserByUserId(idUser);
    }

    private void deactivateAllAllotmentUserByAllotmentId(Integer allotmentId) {
        allotmentUserService.deactivateAllAllotmentUserByAllotmentId(allotmentId);
    }

    private void deactivateAllotmentUserByAllotmentId(Integer allotmentId, User user) {
        allotmentUserService.deactivateAllotmentUserByAllotmentId(allotmentId, user);
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

    private String encodedPassword() {
        return passwordEncoder().encode("test");
    }
}
