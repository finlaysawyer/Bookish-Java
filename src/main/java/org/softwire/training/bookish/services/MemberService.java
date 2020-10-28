package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService extends DatabaseService {

    public List<Member> getAllMembers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM members")
                        .mapToBean(Member.class)
                        .list()
        );
    }

    public void addMember(Member member) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO members VALUES (:member_id, :name)")
                        .bind("member_id", member.getMember_id())
                        .bind("name", member.getName())
                        .execute()
        );
    }

    public void deleteMember(int member_id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM members WHERE member_id = :member_id")
                        .bind("member_id", member_id)
                        .execute()
        );
    }

    public void editMember(int member_id, String new_name) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE members SET name = :name WHERE member_id = :member_id")
                        .bind("name", new_name)
                        .bind("member_id", member_id)
                        .execute()
        );
    }
}
