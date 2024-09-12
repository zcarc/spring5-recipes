package pojo.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {

    @Autowired
    SequenceDao sequenceDao;

    public SequenceService(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }

    public void setSequenceDao(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }

    public String generate(String id) {
        Sequence sequence = sequenceDao.getSequence(id);
        int nextValue = sequenceDao.getNextValue(id);
        return sequence.getPrefix() + nextValue + sequence.getSuffix();
    }
}
