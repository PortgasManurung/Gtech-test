package com.ecommerce.gtech.service;

import com.ecommerce.gtech.models.dto.ChangePasswordRequest;
import com.ecommerce.gtech.models.entity.Customer;
import com.ecommerce.gtech.models.entity.Mail;
import com.ecommerce.gtech.models.entity.PasswordToken;
import com.ecommerce.gtech.models.entity.User;
import com.ecommerce.gtech.repositories.CustomerRepositories;
import com.ecommerce.gtech.repositories.PasswordTokenRepositories;
import com.ecommerce.gtech.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.context.Context;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private PasswordTokenRepositories passwordTokenRepositories;

    @Autowired
    private CustomerRepositories customerRepositories;

    @Autowired
    private MailService mailService;
    

    @Value("${site.Url}/")
    private String mySite;


    public String resetPassword(String email)
    {
        User user = userRepositories.findUserByEmail(email);
        if (user == null) {
            return "User Not Found";
        }
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user.getId(), token,user);

        Customer customer = customerRepositories.getById(user.getId());

        Mail mail = new Mail();
        mail.setSubject("Forgot Password");
        mail.setTo(customer.getEmail());
        mailService.sendSimpleMessage(mail,registrationContext(mySite+"forgotPassword?token="+token,user.getUsername()));
        return "Success, Check Your Email.\n Change your password in 30 Minutes";
    }

    public String changePassword(ChangePasswordRequest request)
    {
        Optional<PasswordToken> passwordToken = passwordTokenRepositories.findUserByToken(request.getToken());
        long milisNow = convertDateMilis(LocalDateTime.now());
        if (!passwordToken.isPresent()) return "Token is not exist, try request forgot password again";
        else if(passwordToken.get().getExpiryDate() < milisNow)
            return "Token is Expired, try request forgot password again";

        User user = getUserById(passwordToken.get().getId());
        user.setPassword(request.getNewPassword());
        userRepositories.save(user);
        return "Password has Changed, Try Login Again";
    }

    private Context registrationContext(String url, String username) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("link", url);
        return context;
    }

    public void createPasswordResetTokenForUser(long user_id, String token,User user) {
        LocalDateTime localDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMinutes(30);
        long expireDate = convertDateMilis(localDateTime);
        PasswordToken myToken = getTokenById(user_id);
        myToken.setToken(token);
        myToken.setExpiryDate(expireDate);
        passwordTokenRepositories.save(myToken);
    }

    public PasswordToken getTokenById(Long id) {
        return passwordTokenRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Token not found"));
    }
    public User getUserById(Long id) {
        return userRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
    }

    public long convertDateMilis(LocalDateTime localDateTime)
    {
        long millis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return millis;
    }



}
