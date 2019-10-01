package me.js.springrestdocs.member;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("members")
@AllArgsConstructor
public class MemberController {

    @PostMapping
    public Member test() {
        Member member = new Member();
        member.setId(1L);
        member.setName("종선");
        return member;
    }
}
