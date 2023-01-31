package org.changsol.api.apps.users.entity;

import lombok.Getter;
import lombok.Setter;
import org.changsol.api.apps.bases.domain.BaseDomainIdentity;
import org.changsol.api.apps.users.dto.UserDto;
import org.changsol.api.apps.users.mappers.UserMapper;
import org.changsol.api.utils.entity.BaseEntityLongId;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

//사용자 정보
@Getter
@Setter
@Entity
public class Users extends BaseDomainIdentity {
    public enum Type {
        ADMIN, //관리자 0
        USER, //사용자 1
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public enum Status {
        MEMBER, //회원
        DORMANT, //휴면 1
        WITHDRAWAL, //탈퇴 2
    }

    @NotNull
    @Column(nullable = false)
    private Type type = Type.USER; // 사용자 종류

    @NotNull
    @Column(unique = true)
    private String loginId; //로그인 ID

    private String password; // 사용자 비밀번호

    private String name;

    private String ci;

    private String di;

    private String phone;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MALE'")
    @NotNull
    private Gender gender = Gender.MALE;
    private LocalDate birth;

    @NotNull
    private Status status = Status.MEMBER; // 회원 상태

    private LocalDateTime dormantAt; //휴면회원전환일

    private LocalDateTime withdrawalAt; //회원탈퇴일

    private LocalDateTime lastLoginAt; //최종로그인시간


    private String remoteAddr;
    private String userAgent;

    private String origin;

    private String requestUri;

    private String method;

    public Integer getAge() {
        return this.birth == null ? null : Period.between(this.birth, LocalDate.now()).getYears() + 1;
    }

    @PrePersist
    public void prePersist() {
        HttpServletRequest request = getCurrentHttpRequest().orElse(null);
        if (request != null) {
            this.remoteAddr = request.getRemoteAddr();
            this.userAgent = request.getHeader("User-Agent");
            this.requestUri = request.getRequestURI();
            this.method = request.getMethod();
            this.origin = request.getHeader("Origin");
        }
    }

    @Transient
    protected static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }
}
