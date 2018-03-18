package security.service;

import model.dao.UserRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.factories.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserFactory.create(userRepositoryDAO.findByUsername(username));
    }
}
