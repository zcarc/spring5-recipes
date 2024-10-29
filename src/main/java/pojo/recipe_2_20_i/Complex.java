package pojo.recipe_2_20_i;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class Complex {
    private int real;
    private int imaginary;

    public Complex() {

    }
}
