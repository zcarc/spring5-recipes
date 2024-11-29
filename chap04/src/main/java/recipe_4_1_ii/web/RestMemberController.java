package recipe_4_1_ii.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import recipe_4_1_ii.domain.Member;
import recipe_4_1_ii.domain.Members;
import recipe_4_1_ii.service.MemberService;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Controller
public class RestMemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members")
    @ResponseBody
    public Members getResetMembers(Model model) {
        Members members = new Members();
        members.addMembers(memberService.findAll());
        return members;
    }

    @GetMapping("/member/{memberId}")
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable("memberId") Long memberId) {
        Member member = memberService.find(memberId);
        if(member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/test/{date}")
    @ResponseBody
    public void date(@PathVariable("date") Date date) {
        System.out.println("date = " + date);
    }
}
