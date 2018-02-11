package model.repository;

import model.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by psawz on 19.04.2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
    Role findByIdRole(Integer id);
    List<Role> findAll();
}
