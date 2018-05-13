package services;

import dto.allotmentUser.UserAllotmentDto;
import model.dao.AllotmentRepositoryDAO;
import model.dao.AllotmentUserRepositoryDAO;
import model.dao.UserRepositoryDAO;
import model.entity.Allotment;
import model.entity.AllotmentUser;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllotmentUserService {

    @Autowired
    private AllotmentRepositoryDAO allotmentRepositoryDAO;

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private AllotmentUserRepositoryDAO allotmentUserRepositoryDAO;

    @Autowired
    private AuthenticationService authenticationService;

    public UserAllotmentDto getActiveUser(){
        AllotmentUser allotmentUser = getAllotmentUserAndActiveTrue(authenticationService.getUser().getIdUser());
        return allotmentUser != null ? new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive()) : new UserAllotmentDto(authenticationService.getUser());
    }

    public List<UserAllotmentDto> getAllAllotmentUserDto() {
        return findAllAllotmentUser();
    }

    public List<UserAllotmentDto> getAllActiveAllotmentUserDto() {
        return findAllActiveAllotmentUser();
    }

    private List<UserAllotmentDto> findAllAllotmentUser() {
        return allotmentUserRepositoryDAO.findAllotmentsUsersByUsers(userRepositoryDAO.findAll()).stream().map(allotmentUser -> new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive())).collect(Collectors.toList());
    }

    private List<UserAllotmentDto> findAllActiveAllotmentUser() {
        return allotmentUserRepositoryDAO.findAllotmentsUsersActive().stream().map(allotmentUser -> new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive())).collect(Collectors.toList());
    }

    protected List<AllotmentUser> findAllotmentUserByAllotmentId(Integer id) {
        return allotmentUserRepositoryDAO.findAllotmentUserByAllotment(allotmentRepositoryDAO.getAllotmentById(id));
    }

    protected void addNewAllotmentUser(Integer allotmentId, User user) {
        AllotmentUser allotmentUser = new AllotmentUser();
        allotmentUser.setUser(user);
        allotmentUser.setAllotment(getAllotmentById(allotmentId));
        allotmentUser.setActive(true);
        persistAllotmentUser(allotmentUser);
    }

    protected void updateAllotmentUser(Integer allotmentId, User user) {
        if (containsAllotmentAndUser(getAllotmentUser(user.getIdUser()), allotmentId, user)) {
            AllotmentUser allotmentUser = allotmentUserRepositoryDAO.findByUserAndAllotment(user, getAllotmentById(allotmentId));
            allotmentUser.setActive(true);
            persistAllotmentUser(allotmentUser);
        } else {
            addNewAllotmentUser(allotmentId, user);
        }
    }

    private boolean containsAllotmentAndUser(final List<AllotmentUser> list, final Integer idAllotment, final User user) {
        return list.stream().anyMatch(o -> o.getAllotment().getIdAllotment().equals(idAllotment) && o.getUser().equals(user));
    }

    protected void deactivateAllotmentUserByUserId(Integer idUser) {
        getAllotmentUser(idUser).forEach(allotmentUser -> {
            allotmentUser.setActive(false);
            persistAllotmentUser(allotmentUser);
        });
    }

    private List<AllotmentUser> getAllotmentUser(Integer idUser) {
        return allotmentUserRepositoryDAO.findAllotmentUserByUser(userRepositoryDAO.findById(idUser));
    }


    /**
     * Method deactivates users and his connection with Allotment without provided user
     *
     * @param idAllotment id of given allotment
     */
    protected void deactivateAllotmentUserByAllotmentId(Integer idAllotment, User user) {
        findAllotmentUserByAllotmentId(idAllotment).forEach(allotmentUser -> {
            allotmentUser.setActive(false);
            if (!allotmentUser.getUser().equals(user)) {
                allotmentUser.getUser().setActive(false);
            }
            persistAllotmentUser(allotmentUser);
            userRepositoryDAO.save(allotmentUser.getUser());
        });
    }

    /**
     * Method deactivates Users and his connection with Allotment
     *
     * @param idAllotment id of given allotment
     */
    protected void deactivateAllAllotmentUserByAllotmentId(Integer idAllotment) {
        findAllotmentUserByAllotmentId(idAllotment).forEach(allotmentUser -> {
            allotmentUser.setActive(false);
            allotmentUser.getUser().setActive(false);
            persistAllotmentUser(allotmentUser);
            userRepositoryDAO.save(allotmentUser.getUser());
        });
    }

    protected AllotmentUser getAllotmentUserAndActiveTrue(Integer idUser) {
        return allotmentUserRepositoryDAO.findAllotmentUserByUserAndActiveTrue(userRepositoryDAO.findById(idUser));
    }

    private Allotment getAllotmentById(Integer allotmentId) {
        return allotmentRepositoryDAO.getAllotmentById(allotmentId);
    }

    private void persistAllotmentUser(AllotmentUser allotmentUser) {
        allotmentUserRepositoryDAO.save(allotmentUser);
    }
}
