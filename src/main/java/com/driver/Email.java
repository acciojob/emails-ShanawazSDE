package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

    if(!oldPassword.equals(password))return;
    int len = newPassword.length();
    if(len < 8)return;
    boolean hasUpper = false;
    for(int i = 0; i < len; i++){
        int ascii = (int) newPassword.charAt(i);
        if(ascii >= 65 && ascii <= 90)hasUpper = true;
    }
    if(!hasUpper) return;

        boolean hasLower = false;
        for(int i = 0; i < len; i++){
            int ascii = (int) newPassword.charAt(i);
            if(ascii >= 97 && ascii <= 122)hasLower = true;
        }
        if(!hasLower)return;

        boolean hasDigit = false;
        for(int i = 0; i < len; i++){
            if(Character.isDigit(newPassword.charAt(i))) hasDigit = true;
        }
        if(!hasDigit)return;

        boolean hasSpecial = false;
        for (int i = 0; i < len; i++) {
            char c = newPassword.charAt(i);
             if(!(Character.isDigit(c) && !(65 <= (int)c && (int)c <= 90) && !(97 <= (int)c && (int)c <= 122)))hasSpecial = true ;
        }
        if(!hasSpecial) return;
        password = newPassword;
    }
}
