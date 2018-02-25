package security.factories;

import model.entity.Role;
import model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import security.dto.JwtUser;

import java.util.Collections;
import java.util.List;

public final class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getIdUser(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword(),
                user.getActive(),
                user.getPhone(),
                grantedAuthority(user.getRole())
        );
    }

    private static List<GrantedAuthority> grantedAuthority(Role role){
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }
}
