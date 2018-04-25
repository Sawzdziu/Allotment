package services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import security.dto.JwtUser;

/**
 * Created by piotrsa on 25/04/18.
 */
@Service
public class AuthenticationService {

    public String getUsername(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Integer getUserId(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUser();
    }
}
