package services;

import dto.UserAllotmentDto;
import model.dao.AllotmentRepositoryDAO;
import model.dao.AllotmentUserRepositoryDAO;
import model.dao.UserRepositoryDAO;
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

    public List<UserAllotmentDto> getAllActiveAllotmentUserDto(){
        return findAllActiveAllotmentUser();
    }

    private List<UserAllotmentDto> findAllAllotmentUser() {
        return allotmentUserRepositoryDAO.findAllotmentsUsersByUsers(userRepositoryDAO.findAll()).stream().map(allotmentUser -> new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive())).collect(Collectors.toList());
    }

    private List<UserAllotmentDto> findAllActiveAllotmentUser() {
        return allotmentUserRepositoryDAO.findAllotmentsUsersActive().stream().map(allotmentUser -> new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive())).collect(Collectors.toList());
    }
}
