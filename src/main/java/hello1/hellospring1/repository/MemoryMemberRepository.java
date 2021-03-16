package hello1.hellospring1.repository;

import hello1.hellospring1.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static Map<String, String> member_map = new HashMap<>();
    private static long seq = 0L;
    public static int pw_leng = 4;

    @Override
    public Member save(Member member) {
        member.setnum(++seq);
        store.put(member.getnum(), member);
        return member;
    }

    public static Long get_num() {
        return ++seq;
    }

    @Override
    public Member save_member(Member member, String id, String pw) {
        pw_inspection(pw);
        //Member mem = save(member);
        Member mem = new Member();
        mem.setnum(get_num());
        mem.setId(id);
        mem.setPw(pw);
        store.put(mem.getnum(), mem);
        member_map.put(id, pw);
        return mem;
    }

    //@Override
    public void pw_inspection(String pw){
        if(pw_leng>pw.length()){
            throw new IllegalStateException("비밀번호가 "+pw_leng+"자 이상 되어야 합니다.");
        }
    }


    public String return_id_pw(String id){
        String pass = member_map.get(id);
        return pass;
    }


    public Boolean check_idpw(String id, String pw){
        String check = member_map.get(id);
        return check.equals(pw);
    }

    @Override
    public Optional<Member> findByNum(Long num) {
        return Optional.ofNullable(store.get(num));
    }

    @Override
    public Optional<Member> findById(String Id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(Id))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
        member_map.clear();
    }
}
