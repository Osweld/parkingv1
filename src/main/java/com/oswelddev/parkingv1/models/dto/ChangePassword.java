package com.oswelddev.parkingv1.models.dto;

import lombok.Data;

@Data
public class ChangePassword {

    Long id;
    String oldPassword;
    String newPassword;
}
