package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.Exceptions.IncorrectPasswordExcepcion;
import com.oswelddev.parkingv1.models.dto.ChangePassword;
import com.oswelddev.parkingv1.models.entity.User;
import com.oswelddev.parkingv1.models.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<User> getUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User updateUser, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setName(updateUser.getName());
            user.setLastname(updateUser.getLastname());
            user.setUsername(updateUser.getUsername());
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            user.setRol(updateUser.getRol());
            user = userRepository.save(user);
            return user;
        }).orElseThrow();
    }

    @Override
    public User deleteUserById(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.deleteById(id);
            return user;
        }).orElseThrow();
    }

    @Override
    public User changePassword(ChangePassword changePassword) {
        return userRepository.findById(changePassword.getId()).map(user -> {
            if (!passwordEncoder.matches(changePassword.getOldPassword(),user.getPassword())) try {
                throw new IncorrectPasswordExcepcion("The password is incorrect");
            } catch (IncorrectPasswordExcepcion e) {
                throw new RuntimeException(e);
            }
            user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
            user = userRepository.save(user);
            return user;
        }).orElseThrow();
    }


}