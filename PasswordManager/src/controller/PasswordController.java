package controller;

import model.Credential;
import utils.PasswordUtils;
import utils.CryptoUtils;
import java.util.ArrayList;
import java.util.List;

public class PasswordController {

    private List<Credential> credentials = new ArrayList<>();

    public String generatePassword(int len, boolean lower, boolean upper, boolean digits, boolean special) {
        return PasswordUtils.generatePassword(len, lower, upper, digits, special);
    }
    
    public boolean isPasswordStrong(String pwd){
        return PasswordUtils.isStrong(pwd);
    }

    public boolean addCredential(String service, String username, String password) {
        if (service == null || service.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        String encryptedPassword = CryptoUtils.encrypt(password);
        if (encryptedPassword == null) {
            return false; // Encryption failed
        }

        Credential cred = new Credential(service, username, encryptedPassword);
        credentials.add(cred);
        return true;
    }

    public List<Credential> getCredentials() {
        return credentials; 
    }
    
    public String getDecryptedPassword(Credential cred){
        if(cred == null) return "";
        return CryptoUtils.decrypt(cred.getPassword());
    }
    
    public boolean updateCredential(String service, String newUsername, String newPassword){
         if (service == null || service.isEmpty() || newUsername == null || newUsername.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            return false;
        }
        
        for(Credential c : credentials){
            if(c.getService().equals(service)){
                String encryptedPassword = CryptoUtils.encrypt(newPassword);
                 if (encryptedPassword == null) {
                    return false;
                }
                c.setUsername(newUsername);
                c.setPassword(encryptedPassword);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteCredential(String service){
         if (service == null || service.isEmpty() ) {
            return false;
        }
        
          for(Credential c : credentials){
            if(c.getService().equals(service)){
                credentials.remove(c);
                return true;
            }
        }
        return false;
    }
}