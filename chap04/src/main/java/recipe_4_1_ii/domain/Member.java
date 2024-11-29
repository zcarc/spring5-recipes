package recipe_4_1_ii.domain;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@XmlRootElement
public class Member {
    private String name;
    private String phone;
    private String email;
}
