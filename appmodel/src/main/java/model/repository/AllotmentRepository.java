package model.repository;

import model.entity.Allotment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by psawz on 19.04.2017.
 */
public interface AllotmentRepository extends JpaRepository<Allotment, Integer> {

    List<Allotment> findAll();
    Allotment findByIdAllotment(Integer id);
}
