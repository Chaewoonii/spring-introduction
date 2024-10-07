package hello.hellospring.domain;

import jakarta.persistence.*;

// JPA: ORM(Object Relation Mapping): 객체와 릴레이션(테이블)을 매핑하는 기술
// @Entity 어노테이션을 활용하여 매핑
@Entity
public class Member {

    // @Id: pk 값임을 명시
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id 값을 db 에서 자동으로 생성하여 넣어주는 것을 IDENTITY 전략이 라고 한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name") // db 의 username 컬럼하고 매핑해줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
