package pojo.sequence;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
public class SequenceGenerator {

    @Autowired
    private PrefixGenerator prefixGenerator;

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
