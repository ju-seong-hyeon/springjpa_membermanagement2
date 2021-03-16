package hello1.hellospring1.domain;

import org.springframework.jdbc.core.RowCallbackHandler;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    @Column(name = "ID")
    private String id;
    @Column(name = "PW")
    private String pw;

    public long getnum(){
        return num;
    }
    public void setnum(Long num){
        this.num = num;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setPw(String pw){
        this.pw = pw;
    }
    public String getPw(){
        return pw;
    }
}
