package model.repository;

import model.entity.Allotment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by psawz on 19.04.2017.
 */
public interface AllotmentRepository extends CrudRepository<Allotment, Integer>{

    List<Allotment> findAll();
    Allotment findByIdAllotment(Integer id);
}
