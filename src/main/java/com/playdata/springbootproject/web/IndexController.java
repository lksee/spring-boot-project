package com.playdata.springbootproject.web;

import com.playdata.springbootproject.config.auth.LoginUser;
import com.playdata.springbootproject.config.auth.dto.SessionUser;
import com.playdata.springbootproject.service.posts.PostsService;
import com.playdata.springbootproject.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) { // 세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
            model.addAttribute("userName", user.getName());
        }

        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto requestsDto = postsService.findById(id);
        model.addAttribute("post", requestsDto);
        return "posts-update";  // src/main/resources/templates/posts-update.mustache
    }
}
