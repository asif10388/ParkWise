package csc305.parkwise.Users.Asif.ParkDirector.StaffAccounts;

import java.io.Serializable;
import csc305.parkwise.Common.Models.User;

public class StaffAccount extends User implements Serializable {
    public StaffAccount() {
    }

    public StaffAccount(int userId, String email, String password, String fullName, String userType) {
        super(userId, email, password, fullName, userType);
    }
}
