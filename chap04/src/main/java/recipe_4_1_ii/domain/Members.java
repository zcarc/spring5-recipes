package recipe_4_1_ii.domain;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Members {

    @XmlElement(name="member")
    private List<Member> members = new ArrayList<>();

    public void addMembers(Collection<Member> members) {
        this.members.addAll(members);
    }
}
