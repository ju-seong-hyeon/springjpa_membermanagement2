package hello1.hellospring1.repository;

import hello1.hellospring1.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.datasource.DataSourceUtils;


import static org.springframework.jdbc.datasource.DataSourceUtils.getConnection;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    public  JdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public Member save(Member member) {
        String sql = "insert into member(id) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setnum(rs.getLong(1));
            } else {
                throw new SQLException("num 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Member save_member(Member member, String id, String pw) {
        return null;
    }


    public Boolean check_idpw(String id, String pw) {
        return null;
    }


    public String return_id_pw(String id) {
        return null;
    }

    @Override
    public Optional<Member> findByNum(Long num) {
        String sql = "select * from member where num = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, num);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setnum(rs.getLong("id"));
                member.setId(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(String Id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setnum(rs.getLong("num"));
                member.setId(rs.getString("id"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }


    @Override
    public List<Member> findAll() {
        return null;
    }

    //@Override
    public void pw_inspection(String pw) {

    }
}