package csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts;

import java.io.Serializable;
import java.util.List;

import csc305.parkwise.Common.Models.User;

public class StaffAccount extends User implements Serializable {
    public StaffAccount() {}

    public StaffAccount(int userId, String email, String password, String fullName, String userType) {
        super(userId, email, password, fullName, userType);
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    public static boolean isValidStaffEmail(String email) {
        return email.contains("@") && email.contains("parkwise") && email.contains(".com");
    }

    public static int generateNewUserId(List<StaffAccount> staffAccounts) {
        if (staffAccounts.isEmpty()) return 1;
        return staffAccounts.getLast().getUserId() + 1;
    }
}
