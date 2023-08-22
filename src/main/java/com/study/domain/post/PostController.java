package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //controller임을 명시
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
    //http://localhost:8080/post/write.do
    @GetMapping("/post/write.do")
    public String openPostWrite(Model model) {
        String title = "제목",
               content = "내용",
               writer = "홍길동";

        model.addAttribute("t", title);
        model.addAttribute("c", content);
        model.addAttribute("w", writer);

        return "post/write";
    }

}