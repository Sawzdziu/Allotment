package services;

import dto.allotmentUser.AllotmentDto;
import dto.allotmentUser.UserAllotmentDto;
import model.dao.AllotmentRepositoryDAO;
import model.dao.AllotmentUserRepositoryDAO;
import model.entity.Allotment;
import model.entity.AllotmentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllotmentService {

    @Autowired
    private AllotmentRepositoryDAO allotmentRepositoryDAO;

    @Autowired
    private AllotmentUserRepositoryDAO allotmentUserRepositoryDAO;

    public List<UserAllotmentDto> getAllAllotments() {
        return getAllAllotment();
    }

    public List<UserAllotmentDto> getAllActiveAllotments() {
        return getAllActiveAllotment();
    }

    public void editAllotment(AllotmentDto allotmentDto) {
        persistAllotment(updateAllotment(allotmentDto));
    }

    private List<UserAllotmentDto> getAllAllotment() {
        return sort(mapAllotmentToUserAllotment());
    }

    private List<UserAllotmentDto> getAllActiveAllotment() {
        return sort(mapToAllotmentUserDto(allotmentUserRepositoryDAO.findAllotmentsUsersActive()));
    }

    private List<UserAllotmentDto> mapToAllotmentUserDto(List<AllotmentUser> allotmentUsers) {
        return allotmentUsers.stream().map(allotmentUser -> new UserAllotmentDto(allotmentUser.getUser(), allotmentUser.getAllotment(), allotmentUser.getActive())).collect(Collectors.toList());
    }

    private List<UserAllotmentDto> sort(List<UserAllotmentDto> allotmentUsers) {
        List<UserAllotmentDto> list = allotmentUsers;
        list.sort(Comparator.comparing(a -> a.getAllotmentDto().getIdAllotment()));
        return list;
    }

    private Allotment updateAllotment(AllotmentDto allotmentDto) {
        Allotment allotment = allotmentRepositoryDAO.getAllotmentById(allotmentDto.getIdAllotment());
        allotment.setBower(allotmentDto.getBower());
        allotment.setSquaremeter(allotmentDto.getSquaremeter());
        allotment.setComposter(allotmentDto.getComposter());
        allotment.setTreeNumber(allotmentDto.getTreeNumber());

        return allotment;
    }

    /**
     * Function creates UserAllotmentDto for all allotments in application. Required for acknowledge if allotment is active or not.
     * @return concatenation of allotments and allotment-user table
     */
    private List<UserAllotmentDto> mapAllotmentToUserAllotment() {
        List<AllotmentUser> allotmentUserList = allotmentUserRepositoryDAO.findAllotmentsUsersActive();
        List<UserAllotmentDto> resultList = new LinkedList<>();
        allotmentRepositoryDAO.findAll().forEach(allotment -> {
            if (!containsAllotment(allotmentUserList, allotment.getIdAllotment())) {
                resultList.add(new UserAllotmentDto(allotment, false));
            }
        });
        resultList.addAll(mapToAllotmentUserDto(allotmentUserList));
        return resultList;
    }

    private void persistAllotment(Allotment allotment) {
        allotmentRepositoryDAO.save(allotment);
    }

    /**
     * Function checks if in AllotmentUser list exist allotment with specified id
     * @param list
     * @param idAllotment
     * @return true if allotment exist in this list
     */
    private boolean containsAllotment(final List<AllotmentUser> list, final Integer idAllotment) {
        return list.stream().anyMatch(o -> o.getAllotment().getIdAllotment().equals(idAllotment));
    }
}
