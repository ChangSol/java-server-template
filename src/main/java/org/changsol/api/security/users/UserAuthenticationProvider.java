package org.changsol.api.security.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
//    private final LogAuthenticationRepository logAuthenticationRepository;
//
//    private final LogAuthenticationService logAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        try {
            SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(username);
            // 패스워드 CHECK
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("password is not correct");
            }

//            logAuthenticationService.create(
//                    details.get("social_type"),
//                    username,
//                    null,
//                    httpServletRequest.getHeader("X-Forwarded-For")
//            );

            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        } catch (AuthenticationException ex) {
//            logAuthenticationService.create(
//                    details.get("social_type"),
//                    username,
//                    ex,
//                    httpServletRequest.getHeader("X-Forwarded-For")
//            );
            throw ex;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
