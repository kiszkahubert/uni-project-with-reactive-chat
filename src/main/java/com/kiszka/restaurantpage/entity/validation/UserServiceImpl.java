package com.kiszka.restaurantpage.entity.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        UserInfo userInfo= new UserInfo();
        userInfo.setEmail(userDto.getEmail());
        userInfo.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userInfo.setRole(userDto.getRole());
        userRepository.save(userInfo);
    }

    @Override
    public UserInfo findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<UserInfo> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfo getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            String email = ((UserDetails)principal).getUsername();
            return userRepository.findByEmail(email);
        }
        return null;
    }

    private UserDto mapToUserDto(UserInfo userInfo){
        UserDto userDto = new UserDto();
        userDto.setEmail(userInfo.getEmail());
        return userDto;
    }
}
