package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Test가 끝날 때 마다 Repository를 초기화 해준다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); // spring이라는 이름의 회원을 셋팅
        repository.save(member); // Repository에 spring 멤버를 저장

        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member); //  member와 result가 같은지
    }

    @Test
    public void findByName() {
        Member member1 = new Member(); // member1 생성
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // member2 생성
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // spring1 이라는 이름을 가진 멤버를 찾아 result 변수에 저장

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member(); // member1 생성
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // member2 생성
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // member를 2명 생성했으니, result의 List Size가 2인지 검증하기
    }
}
