package pojo.sequence;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatePrefixGenerator implements PrefixGenerator {

    @Override
    public String getPrefix() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }
}
