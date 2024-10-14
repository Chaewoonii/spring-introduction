package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    /* Jdbc 사용 시 DataSource 필요
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
     */
    /* JPA 사용 시 EntityManager 필요
//    @PersistenceContext
    private EntityManager em; // jpa에서 사용되는 EntityManager를 주입받음.
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
     */

    // JpaRepository<T, ID>를 상속하는 인터페이스가 있는 경우(이 프로젝트의 경우 SpringDataJpaMemberRepository)
    // 스프링 데이터 jpa에서 구현체를 만들어줌. 주입받아 사용하면 됨
    private final MemberRepository memberRepository;

    @Autowired // 생략 가능
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    AOP 등록 / 순환참조 발생...
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

}

