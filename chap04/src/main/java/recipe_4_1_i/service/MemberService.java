package recipe_4_1_i.service;

import recipe_4_1_i.domain.Member;

import java.util.Collection;

public interface MemberService {

    Collection<Member> findAll();
    Member find(long id);
}
