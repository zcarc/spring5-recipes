package recipe_4_1_i.web;

//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import recipe_4_1_i.domain.Members;
//import recipe_4_1_i.service.MemberService;

//@AllArgsConstructor
//@Controller
//public class RestMemberController {
//
//    private final MemberService memberService;
//
//    @GetMapping("/members")
//    public String getResetMembers(Model model) {
//        Members members = new Members();
//        members.addMembers(memberService.findAll());
//        model.addAttribute("members", members);
//        return "membertemplate";
//    }
//}
