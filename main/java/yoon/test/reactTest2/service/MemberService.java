package yoon.test.reactTest2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.test.reactTest2.domain.Members;
import yoon.test.reactTest2.enums.Role;
import yoon.test.reactTest2.repository.MemberRepository;
import yoon.test.reactTest2.vo.request.MemberDto;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private MemberDto memberToDto(Members member){
        return new MemberDto(member.getEmail(), member.getPassword());
    }
    public MemberDto join(MemberDto dto){

        Members member = Members.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();

        return memberToDto(memberRepository.save(member));
    }

}
