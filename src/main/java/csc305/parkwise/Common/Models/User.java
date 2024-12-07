package csc305.parkwise.Common.Models;

import java.io.Serializable;

public class User implements Serializable {
    protected int userId;

    protected String email;
    protected String password;

    protected String fullName;
    protected String userType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User() {
    }

    public User(int userId, String email, String password, String fullName, String userType) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.fullName = fullName;
    }

    public static String generateRandomPassword() {
        StringBuilder generatedPassword = new StringBuilder();
        String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] specialCharacters = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")"};
        String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        for(int i = 0; i < 4; i++) {
            generatedPassword.append(digits[(int) (Math.random() * digits.length)]);
            generatedPassword.append(alphabets[(int) (Math.random() * alphabets.length)]);
            generatedPassword.append(specialCharacters[(int) (Math.random() * specialCharacters.length)]);
        }

        return generatedPassword.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
