package hello1.hellospring1.repository;

import hello1.hellospring1.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member save_member(Member member, String id, String pw) {
        member.setId(id);
        member.setPw(pw);
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByNum(Long num) {
        Member member = em.find(Member.class, num);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findById(String Id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", Id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //@Override
    public void pw_inspection(String pw) {

    }
}
