//package ServletTraining.servlet;
//
//import ServletTraining.servlet.domain.MemberRepository;
//import ServletTraining.servlet.domain.Member;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//public class MemberRepositoryTest {
//    MemberRepository memberRepository = MemberRepository.getInstance();
//
//    @AfterEach
//    void afterEach() {
//        memberRepository.clearStore();
//    }
//
//    @Test
//    void save() {
//        //given
//        Member member = new Member("hello",20);
//        //when
//        Member saveMember = memberRepository.save(member);
//        //then
//        Member findMember = memberRepository.findId(saveMember.getId());
//
//        Assertions.assertThat(findMember).isEqualTo(saveMember);
//
//
//    }
//}
