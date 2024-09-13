package pojo.sequence;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
public class SequenceGenerator {

//    @Autowired
//    @Qualifier("numberPrefixGenerator")
//    private PrefixGenerator prefixGenerator;

    private PrefixGenerator prefixGenerator;

    @Autowired
    public void myOwnCustomInjectionName(@Qualifier("datePrefixGenerator") PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    private String suffix;
    private int initial;
    private AtomicInteger counter = new AtomicInteger();

    public String getSequence() {

        StringBuilder builder = new StringBuilder();

        builder.append(prefixGenerator.getPrefix())
                .append(initial)
                .append(counter.getAndIncrement())
                .append(suffix);
        return builder.toString();
    }
}
