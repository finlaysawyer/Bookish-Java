package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Member;

public class ViewMemberPageModel {
    private Member member;
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
}
