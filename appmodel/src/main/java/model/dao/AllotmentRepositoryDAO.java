package model.dao;

import model.entity.Allotment;
import model.repository.AllotmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by psawz on 09.05.2017.
 */
@Repository
public class AllotmentRepositoryDAO {

    @Autowired
    private AllotmentRepository allotmentRepository;

    public List<Allotment> findAll(){
        return allotmentRepository.findAll();
    }

    public Allotment getAllotmentById(Integer id){
        return allotmentRepository.findByIdAllotment(id);
    }

    public void save(Allotment allotment){
        allotmentRepository.save(allotment);
    }
}
