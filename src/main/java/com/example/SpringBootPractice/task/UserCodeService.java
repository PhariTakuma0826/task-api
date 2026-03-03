package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCodeService {

    private final SequenceRepository sequenceRepository;

    public UserCodeService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Transactional
    public String nextUserCode() {
        Sequence seq = sequenceRepository.findByNameForUpdate("user_code")
                .orElseThrow(() -> new IllegalStateException("sequences.user_code が存在しません"));

        long next = seq.getCurrentValue() + 1;
        seq.setCurrentValue(next); // JPAが更新してくれる

        return String.format("U%03d", next); // U001, U002...
    }
}
