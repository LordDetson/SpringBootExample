package by.babanin.service;

import by.babanin.dao.UserRepository;
import by.babanin.model.Role;
import by.babanin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailSend mailSend;

    public UserService(UserRepository userRepository, MailSend mailSend) {
        this.userRepository = userRepository;
        this.mailSend = mailSend;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public boolean addUser(User user) {
        User byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello %s!\n" +
                            "Welcome to Sweater. Please, visit this link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSend.send(user.getEmail(), "Activation code mail", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User byActivationCode = userRepository.findByActivationCode(code);
        if (byActivationCode == null) {
            return false;
        }
        byActivationCode.setActivationCode(null);
        userRepository.save(byActivationCode);
        return true;
    }
}
