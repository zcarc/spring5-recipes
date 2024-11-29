package recipe_4_1_ii.service;

import recipe_4_1_ii.domain.Member;

import java.util.Collection;

public interface MemberService {

    Collection<Member> findAll();
    Member find(long id);
}
