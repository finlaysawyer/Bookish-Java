package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.models.page.MembersPageModel;
import org.softwire.training.bookish.models.page.ViewBookPageModel;
import org.softwire.training.bookish.models.page.ViewMemberPageModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("")
    ModelAndView members(@RequestParam(value = "search", required = false) String search) {
        MembersPageModel membersPageModel = new MembersPageModel();
        List<Member> allMembers;
        if (search == null) {
            allMembers = memberService.getAllMembers();
        } else {
            allMembers = memberService.getMemberSearch(search);
        }
        membersPageModel.setMembers(allMembers);
        return new ModelAndView("members", "model", membersPageModel);
    }

    @RequestMapping("/add-member")
    ModelAndView addMember() {
        return new ModelAndView("add-member");
    }

    @RequestMapping("/add-member/action")
    RedirectView addMemberAction(@ModelAttribute Member member) {

        memberService.addMember(member);

        return new RedirectView("/members");
    }

    @RequestMapping("/view-member")
    ModelAndView viewMember(@RequestParam int member_id) {

        List<Member> allMembers = memberService.getMember(member_id);
        ViewMemberPageModel viewMemberPageModel = new ViewMemberPageModel();
        viewMemberPageModel.setMember(allMembers.get(0));

        return new ModelAndView("view-member", "model", viewMemberPageModel);
    }

    @RequestMapping("/delete-member")
    RedirectView deleteMember(@RequestParam int member_id) {

        memberService.deleteMember(member_id);

        return new RedirectView("/members");
    }

    @RequestMapping("/edit-member")
    RedirectView editMember(@RequestParam int member_id, @RequestParam String new_name) {

        memberService.editMember(member_id, new_name);

        return new RedirectView("/members");
    }
}
