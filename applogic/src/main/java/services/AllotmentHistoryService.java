package services;

import dto.AllotmentHistoryDto;
import model.dao.AllotmentRepositoryDAO;
import model.dao.AllotmentUserRepositoryDAO;
import model.entity.Allotment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AllotmentHistoryService {

    @Autowired
    private AllotmentRepositoryDAO allotmentRepositoryDAO;

    @Autowired
    private AllotmentUserRepositoryDAO allotmentUserRepositoryDAO;

    public AllotmentHistoryDto getAllotmentHistory(Integer id){
        return findHistoryUsers(id);
    }

    private AllotmentHistoryDto findHistoryUsers(Integer id){
        AllotmentHistoryDto allotmentHistoryDto = new AllotmentHistoryDto(id);
        allotmentUserRepositoryDAO.findAllotmentUserByAllotment(findById(id)).forEach(allotmentUser -> allotmentHistoryDto.addUser(allotmentUser.getUser()));
        return allotmentHistoryDto;
    }

    private Allotment findById(Integer id){
        return allotmentRepositoryDAO.getAllotmentById(id);
    }
}
