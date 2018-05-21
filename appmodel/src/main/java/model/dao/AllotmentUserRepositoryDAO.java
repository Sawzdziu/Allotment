package model.dao;

import model.entity.Allotment;
import model.entity.AllotmentUser;
import model.entity.User;
import model.repository.AllotmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Repository
public class AllotmentUserRepositoryDAO {

    @Autowired
    private AllotmentUserRepository allotmentUserRepository;

    @Transactional
    public List<AllotmentUser> findAllAllotmentUser(){
        return allotmentUserRepository.findAll();
    }

    @Transactional
    public List<AllotmentUser> findAllotmentUserByUser(User user) {
        return allotmentUserRepository.findByUser(user).orElse(Collections.emptyList());
    }

    @Transactional
    public AllotmentUser findAllotmentUserByUserAndActiveTrue(User user) {
        return allotmentUserRepository.findByUserAndActiveTrue(user).orElse(null);
    }

    @Transactional
    public List<AllotmentUser> findAllotmentUserByAllotment(Allotment allotment) {
        return allotmentUserRepository.findByAllotment(allotment).orElse(Collections.emptyList());
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

    @Transactional
    public AllotmentUser findByUserAndAllotment(User user, Allotment allotment){
        return allotmentUserRepository.findByUserAndAllotment(user, allotment).orElse(null);
    }

    @Transactional
    public void save(AllotmentUser allotmentUser){
        allotmentUserRepository.save(allotmentUser);
    }
}
