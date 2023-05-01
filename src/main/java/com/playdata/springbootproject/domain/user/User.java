package com.playdata.springbootproject.domain.user;

import com.playdata.springbootproject.domain.AuditingEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지 결정한다.
    // 기본적으로는 int로 된 숫자가 저장된다.
    // 숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없다.
    // 그래서 문자열(EnumType.STRING)로 저장될 수 있도록 선언한다.
    // 이렇게 선언하면, 데이터베이스로 저장할 때 Enum의 이름이 저장된다.
    // 즉, Role이라는 Enum이 있다면, 그 중 이름이 USER라면 USER가 데이터베이스에 저장된다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    /**
     * 사용자 정보를 업데이트한다.
     * @param name
     * @param picture
     * @return 업데이트된 사용자 정보
     */
    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    /**
     * 사용자의 권한을 가져온다.
     * @return 사용자의 권한
     */
    public String getRoleKey() {
        return this.role.getKey();
    }
}
