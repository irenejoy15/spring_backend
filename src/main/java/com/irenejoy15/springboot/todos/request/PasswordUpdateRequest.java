package com.irenejoy15.springboot.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {
    @NotEmpty(message = "Old password is required")
    @Size(min = 6, max = 30, message = "Old password must be between 6 and 30 characters long")
    private String oldPassword;

    @NotEmpty(message = "New password is required")
    @Size(min = 6, max = 30, message = "New password must be between 6 and 30 characters long")
    private String newPassword;

    @NotEmpty(message = "Confirm new password is required")
    @Size(min = 6, max = 30, message = "Confirm new password must be between 6 and 30 characters long")
    private String newPassword2;

    public PasswordUpdateRequest(
            @NotEmpty(message = "Old password is required") @Size(min = 6, max = 30, message = "Old password must be between 6 and 30 characters long") String oldPassword,
            @NotEmpty(message = "New password is required") @Size(min = 6, max = 30, message = "New password must be between 6 and 30 characters long") String newPassword,
            @NotEmpty(message = "Confirm new password is required") @Size(min = 6, max = 30, message = "Confirm new password must be between 6 and 30 characters long") String newPassword2) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPassword2 = newPassword2;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
