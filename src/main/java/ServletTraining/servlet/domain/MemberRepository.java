package ServletTraining.servlet.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 싱글톤으로 개발할 경우 private로 생성자를 막아야 한다
 */
public class MemberRepository {

    // Map 형태로 id와 username,age를 가져오겠다
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // static 사용

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }


    // 멤버 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 멤버 특정 ID찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    // 전체 멤버 불러오기
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 저장정보 삭제하기
    public void clearStore() {
        store.clear();
    }
}
