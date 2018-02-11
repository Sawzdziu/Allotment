package model.dao;

import model.entity.Role;
import model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by psawz on 09.05.2017.
 */
@Repository
public class RoleRepositoryDAO {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role getRoleById(Integer id){
        return roleRepository.findByIdRole(id);
    }

    @Transactional
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @Transactional
    public Role getByName(String name){
        return roleRepository.findByName(name);
    }
}
