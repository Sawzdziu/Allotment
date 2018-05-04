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

    protected void deactivateAllotmentUserByUserId(Integer idUser){
        AllotmentUser allotmentUser = getAllotmentUser(idUser);
        allotmentUser.setActive(false);
        persistAllotmentUser(allotmentUser);
    }

    private AllotmentUser getAllotmentUser(Integer idUser){
        return allotmentUserRepositoryDAO.findAllotmentUserByUser(userRepositoryDAO.findById(idUser));
    }


    /**
     * Method deactivates User and his connection with Allotment
     *
     * @param idAllotment id of given allotment
     */
    protected void deactivateAllotmentUserByAllotmentId(Integer idAllotment) {
        findAllotmentUserByAllotmentId(idAllotment).forEach(allotmentUser -> {
            allotmentUser.setActive(false);
            allotmentUser.getUser().setActive(false);
            persistAllotmentUser(allotmentUser);
            userRepositoryDAO.save(allotmentUser.getUser());
        });
    }

    private Allotment getAllotmentById(Integer allotmentId) {
        return allotmentRepositoryDAO.getAllotmentById(allotmentId);
    }

    private void persistAllotmentUser(AllotmentUser allotmentUser) {
        allotmentUserRepositoryDAO.save(allotmentUser);
    }
}
