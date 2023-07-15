package yoon.test.reactTest2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.reactTest2.domain.Members;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findMembersByEmail(String email);

    Optional<?> findByEmail(String email);

}
