package services;

import dto.UserAllotmentDto;
import model.dao.AllotmentRepositoryDAO;
import model.dao.AllotmentUserRepositoryDAO;
import model.entity.Allotment;
import model.entity.AllotmentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllotmentService {

    @Autowired
    private AllotmentRepositoryDAO allotmentRepositoryDAO;

    @Autowired
    private AllotmentUserRepositoryDAO allotmentUserRepositoryDAO;

    public List<UserAllotmentDto> getAllAllotments() {
        return getAllActiveAllotment();
    }

    private List<Allotment> getAllAllotment() {
        return allotmentRepositoryDAO.findAll();
    }

    private List<UserAllotmentDto> getAllActiveAllotment() {
        return sort(mapToUserDto(allotmentUserRepositoryDAO.findAllotmentsUsersActive()));
    }

    private List<UserAllotmentDto> mapToUserDto(List<AllotmentUser> allotmentUsers) {
        return allotmentUsers.stream().map(allotmentUser -> new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive())).collect(Collectors.toList());
    }

    private List<UserAllotmentDto> sort(List<UserAllotmentDto> allotmentUsers) {
        List<UserAllotmentDto> list = allotmentUsers;
        list.sort(Comparator.comparing(a -> a.getAllotmentDto().getIdAllotment()));
        return list;
    }
}
