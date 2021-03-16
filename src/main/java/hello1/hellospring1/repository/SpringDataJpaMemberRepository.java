package hello1.hellospring1.repository;

import hello1.hellospring1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findById(String Id);

    //@Override
    Member save_member(Member member, String id, String pw);

    //@Override

    //Iterable<Member> saveAll(Iterable<Member> members);
    //Member save_member(Member member, String id, String pw);
}
