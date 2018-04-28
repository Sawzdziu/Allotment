package services;

import model.dao.UserRepositoryDAO;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import security.dto.JwtUser;

/**
 * Created by piotrsa on 25/04/18.
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    public String getUsername(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Integer getUserId(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
    }

    public User getUser(){
        return userRepositoryDAO.findByUsername(getUsername());
    }
}
