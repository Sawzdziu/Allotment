package model.dao;

import model.entity.Allotment;
import model.entity.AllotmentUser;
import model.entity.User;
import model.repository.AllotmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AllotmentUserRepositoryDAO {

    @Autowired
    private AllotmentUserRepository allotmentUserRepository;

    @Transactional
    public AllotmentUser findAllotmentUserByUser(User user) {
        return allotmentUserRepository.findByUser(user).orElseThrow(NoResultException::new);
    }

    @Transactional
    public List<AllotmentUser> findAllotmentUserByAllotment(Allotment allotment) {
        return allotmentUserRepository.findByAllotment(allotment).orElseThrow(NoResultException::new);
    }

    @Transactional
    public List<AllotmentUser> findAllotmentsUsersByUsers(List<User> users){
        return allotmentUserRepository.findAllByUserIn(users).orElseThrow(NoResultException::new);
    }

    @Transactional
    public List<AllotmentUser> findAllotmentsUsersActive(){
        return allotmentUserRepository.findAllByActiveTrue().orElseThrow(NoResultException::new);
    }

    @Transactional
    public List<AllotmentUser> findHistoryUsers(Allotment allotment){
        return allotmentUserRepository.findAllByAllotmentAndActiveFalse(allotment).orElseThrow(NoResultException::new);
    }
}
