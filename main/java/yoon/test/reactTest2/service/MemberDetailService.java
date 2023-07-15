package yoon.test.reactTest2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yoon.test.reactTest2.domain.Members;
import yoon.test.reactTest2.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Members member = memberRepository.findMembersByEmail(username);
        if(member == null)
            throw new UsernameNotFoundException(username);
        return (UserDetails) member;
    }
}
