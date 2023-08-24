package com.study.domain.post;

import com.study.common.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller //controller임을 명시
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
    //http://localhost:8080/post/write.do
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) { //@RequestParam 화면(HTML)에서 보낸 파라미터를 전달받는 데 사용됨, required 속성을 false로 지정하지 않으면, id가 파라미터로 넘어오지 않았을 때 예외가 발생한다
        if (id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        return "post/write";
    }

//    // 신규 게시글 생성
//    @PostMapping("/post/save.do")
//    public String savePost(final PostRequest params) {
//        postService.savePost(params);
//        return "redirect:/post/list.do";
//    }

    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 리스트 페이지
    @GetMapping("/post/list.do")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

//    // 기존 게시글 수정
//    @PostMapping("/post/update.do")
//    public String updatePost(final PostRequest params) {
//        postService.updatePost(params);
//        return "redirect:/post/list.do";
//    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

//    // 게시글 삭제
//    //물리적인 삭제가 아닌 삭제 여부(delete_yn)컬럼의 값을 변경하는 논리 삭제
//    @PostMapping("/post/delete.do")
//    public String deletePost(@RequestParam final Long id) {
//        postService.deletePost(id);
//        return "redirect:/post/list.do"; //삭제 후 리스트 페이지로
//    }

    // 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id, Model model) {
        postService.deletePost(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }



}