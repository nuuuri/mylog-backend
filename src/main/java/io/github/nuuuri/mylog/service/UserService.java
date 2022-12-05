package io.github.nuuuri.mylog.service;

import io.github.nuuuri.mylog.data.entity.User;
import io.github.nuuuri.mylog.data.repository.UserRepository;
import io.github.nuuuri.mylog.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long createUser(UserDTO.Request userRequestDTO) {
        
        return userRepository.save(userRequestDTO.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public UserDTO.Response getUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        return new UserDTO.Response(user);
    }
}
