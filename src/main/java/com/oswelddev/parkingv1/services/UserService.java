package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.models.dto.ChangePassword;
import com.oswelddev.parkingv1.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    Page<User> getUserPage(Pageable pageable);
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(User updateUser,Long id);
    User deleteUserById(Long id);
    User changePassword(ChangePassword changePassword);

}
