package yoon.test.reactTest2.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import yoon.test.reactTest2.service.MemberDetailService;

@Component
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final MemberDetailService memberDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDetails member = memberDetailService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new BadCredentialsException(username);
        }
        return new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
