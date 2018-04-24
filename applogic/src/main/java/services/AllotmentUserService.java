package services;

import dto.UserAllotmentDto;
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

    private void addNewAllotmentUser(Allotment allotment, User user) {
        AllotmentUser allotmentUser = new AllotmentUser();
        allotmentUser.setUser(user);
        allotmentUser.setAllotment(allotment);
        allotmentUser.setActive(true);
    }


    /**
     * Method deactivates User and his connection with Allotment
     *
     * @param allotment given allotment
     */
    private void deactivateAllotmentUser(Allotment allotment) {
        allotmentUserRepositoryDAO.findHistoryUsers(allotment).stream().forEach(allotmentUser -> {
            allotmentUser.setActive(false);
            allotmentUser.getUser().setActive(false);
        });
    }

    private void persistAllotmentUser(AllotmentUser allotmentUser) {
        allotmentUserRepositoryDAO.save(allotmentUser);
    }
}
