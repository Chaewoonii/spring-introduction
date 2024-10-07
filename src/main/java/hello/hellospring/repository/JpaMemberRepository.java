package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    // SpringBoot 에서는 application.property 의 정보와 database connection 정보를 기반으로 EntityManager 를 자동 생성
    // EntityManager 는 내부에 DataSource 를 들고 있어 db 와 통신하는 것들을 처리할 수 있다
    // JPA 를 사용하려면 EntityManager 를 주입받아야 한다.
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist: 영속하다. jpa 에서 insert 쿼리를 날려 db에 정보를 저장.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // 파라미터 셋팅: ":name"에 파라미터로 들어온 name 변수를 할당
                .getResultList();
        return result.stream().findAny(); // 1개만 반환
    }

    @Override
    public List<Member> findAll() {
        // jpql: 보통 쿼리는 테이블을 대상으로 날림. jpql은 객체를 대상으로 쿼리를 날리면 sql로 번역됨
        // select m from Member (*as 생략됨) m: Member를 대상으로 조회해
        // m: sql 이었으면 * 이나 m.id, m.name 등 컬럼을 선택해야하는데, jpsl은 객체 자체를 선택함.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
