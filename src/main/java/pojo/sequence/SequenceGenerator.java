package pojo.sequence;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
public class SequenceGenerator {

    @Autowired
    private PrefixGenerator[] prefixGenerators;


    private String suffix;
    private int initial;
    private AtomicInteger counter = new AtomicInteger();

    public String getSequence() {

        StringBuilder builder = new StringBuilder();

        for (PrefixGenerator prefixGenerator : prefixGenerators) {
            builder.append(prefixGenerator.getPrefix());
            builder.append("-");
        }

        builder.append(initial)
                .append(counter.getAndIncrement())
                .append(suffix);
        return builder.toString();
    }
}
