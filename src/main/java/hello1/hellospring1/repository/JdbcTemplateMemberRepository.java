package hello1.hellospring1.repository;

import hello1.hellospring1.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static hello1.hellospring1.repository.MemoryMemberRepository.pw_leng;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("num");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setnum(key.longValue());
        return member;
    }

    @Override
    public Member save_member(Member member, String id, String pw) {
        pw_inspection(pw);
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("num");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("pw", pw);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setnum(key.longValue());
        return member;
    }



    public Boolean check_idpw(String id, String pw) {
        return null;
    }


    public String return_id_pw(String id) {
        List<Member> result = jdbcTemplate.query("select pw from member where id = ?",
                memberRowMapper(), id);
        //return result.stream().findAny();
        return null;
    }

    @Override
    public Optional<Member> findByNum(Long num) {
        List<Member> result = jdbcTemplate.query("select * from member where num = ?",
                memberRowMapper(), num);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findById(String Id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",
                memberRowMapper(), Id);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    //@Override
    public void pw_inspection(String pw) {
        if(pw_leng>pw.length()){
            throw new IllegalStateException("비밀번호가 "+pw_leng+"자 이상 되어야 합니다.");
        }
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) ->{
            Member member = new Member();
            member.setnum(rs.getLong("NUM"));
            member.setId(rs.getString("ID"));
            member.setPw(rs.getString("PW"));
            return member;
        };
    }
}
