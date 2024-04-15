package ServletTraining.servlet.domain;

import  org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }  // 테스트가 끝날때마다 초기화 시킨다

    @Test
    void getInstance() {
    }

    @Test
    void save() {
        //given
        Member Member = new Member("hello",20);
        //when
        ServletTraining.servlet.domain.Member saveMember = memberRepository.save(Member);
        //then
        ServletTraining.servlet.domain.Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findId() {

    }

    @Test
    void findAll() {
    }

    @Test
    void clearStore() {
    }
}