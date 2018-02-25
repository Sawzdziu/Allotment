package security.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class JwtUser implements UserDetails {

    private Integer idUser;
    private String email;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Boolean isActive;
    private String phone;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Integer idUser, String email, String name, String surname, String username, String password, Boolean isActive, String phone, Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.phone = phone;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
