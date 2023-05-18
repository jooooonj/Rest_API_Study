package com.ll.rest.boundedContext.member.dto;

import com.ll.rest.boundedContext.member.entity.Member;

import java.time.LocalDateTime;

public class MemberDto {
    private Long id;
    private LocalDateTime regDate;
    private String username;

    public MemberDto(Member member){
        this.id = member.getId();
        this.regDate = member.getCreateDate();
        this.username = member.getUsername();
    }
}
