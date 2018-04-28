package servicesTest;

import org.junit.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import security.dto.JwtUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AuthenticationSetup {

    @Before
    public void setUp(){
        JwtUser applicationUser = new JwtUser();
        applicationUser.setUsername("Admin");

        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(applicationUser);
    }
}
