package hello1.hellospring1.service;

import hello1.hellospring1.domain.Member;
import hello1.hellospring1.repository.MemberRepository;
import hello1.hellospring1.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getnum();
    }

    public Long join(Member member, String id, String pw){
        validateDuplicateMember(member);
        memberRepository.save_member(member, id, pw);
        //memberRepository.save_member(id, pw);
        return member.getnum();
    }


    private void validateDuplicateMember(Member member){
        memberRepository.findById(member.getId())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    private void validateDuplicateMember(String id){
        memberRepository.findById(id)
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    public void login(String id, String pw){
        if(!memberRepository.return_id_pw(id).equals(pw)){
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
    }

     */

    /*
    public Boolean login_inspection(String id, String pw){
        String pass = memberRepository.return_id_pw(id);
        Boolean log_effecive = memberRepository.check_idpw(id, pw).equals(pass);
        return log_effecive;
    }

     */
    /*
    public void login_inspection(String id){
        String pass = memberRepository.return_id_pw(id);
        Boolean c = memberRepository.check_idpw(id, pass);
        if(!c){
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
    }

     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String number_id){
        return memberRepository.findById(number_id);
    }

}
