package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserCodeService userCodeService;

    public UserService(UserRepository userRepository,
                       UserCodeService userCodeService) {
        this.userRepository = userRepository;
        this.userCodeService = userCodeService;
    }

    @Transactional
    public void createUser(String email, String rawPassword) {

        String code = userCodeService.nextUserCode();

        User user = new User();
        user.setUserCode(code);
        user.setEmail(email);
        user.setPassword(rawPassword);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
