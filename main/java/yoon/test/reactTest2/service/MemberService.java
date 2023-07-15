package yoon.test.reactTest2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.test.reactTest2.domain.Members;
import yoon.test.reactTest2.enums.Role;
import yoon.test.reactTest2.repository.MemberRepository;
import yoon.test.reactTest2.vo.request.MemberDto;
import yoon.test.reactTest2.vo.response.MemberResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private MemberResponse memberToResponse(Members member){
        return new MemberResponse(member.getEmail(), member.getRoleKey(), member.getRegdate());
    }
    public MemberResponse join(MemberDto dto){
        if(dto.getEmail() == null && dto.getPassword() == null)
            return null;

        Members member = Members.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();

        return memberToResponse(memberRepository.save(member));
    }

    public MemberResponse login(MemberDto dto)throws UsernameNotFoundException, BadCredentialsException{
        String username = dto.getEmail();
        String password = dto.getPassword();

        Members member = memberRepository.findMembersByEmail(username);

        if(member == null) {
            System.out.println("UsernameNotFound");
            throw new UsernameNotFoundException(username);
        }
        if(!passwordEncoder.matches(password, member.getPassword())) {
            System.out.println("BadCredential");
            throw new BadCredentialsException(username);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return memberToResponse(member);
    }

}
