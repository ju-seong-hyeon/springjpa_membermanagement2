package hello1.hellospring1.repository;

import hello1.hellospring1.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member save_member(Member member, String id, String pw);
    //Boolean check_idpw(String id, String pw);
    //String return_id_pw(String id);
    Optional<Member> findByNum(Long num);
    Optional<Member> findById(String Id);
    List<Member> findAll();
    //void pw_inspection(String pw);
}
