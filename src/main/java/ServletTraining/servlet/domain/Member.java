package ServletTraining.servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id; // 식별자 ID값
    // id는 Member를 회원 저장소에 저장하면 회원 저장소가 할당한다
    private String username;
    private int age;

    // 생성자
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
