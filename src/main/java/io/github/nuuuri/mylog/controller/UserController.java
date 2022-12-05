package io.github.nuuuri.mylog.controller;

import io.github.nuuuri.mylog.dto.UserDTO;
import io.github.nuuuri.mylog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public Long createUser(@RequestBody UserDTO.Request userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity getUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok()
                    .body(userService.getUser(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("유저 정보 조회 실패");
        }
    }
}
