package com.study.domain.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    //사용자로부터 입력 받는 필드의 "name"과 맴버 변수명이 동일해야 한다
    private Long id; // PK
    private String title; // 제목 
    private String content; // 내용
    private String writer; // 작성자
    private Boolean noticeYn; // 공지글 여부

}
