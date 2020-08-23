package com.secure.secure_back_end.configuration.insert_on_startup;

import com.secure.secure_back_end.repositories.AuthorityRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoData
{

    private final static String PASSWORD_1234 = "$2a$10$N84ugQMjD25QvdyIOBWEpeZLAQOwzjFAQdaIGLQkQY.2JGIMr06C6";
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @Autowired
    public DemoData(AuthorityRepository authorityRepository, UserRepository userRepository)
    {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

//    @EventListener
//    public void appReady(ApplicationReadyEvent event)
//    {
//        Set<Authority> authorities = new HashSet<>();
//        authorities.add(new Authority("ROLE_SUBMITTER", 1));
//        authorities.add(new Authority("ROLE_DEVELOPER", 2));
//        authorities.add(new Authority("ROLE_PROJECT_MANAGER", 3));
//        authorities.add(new Authority("ROLE_ADMIN", 4));
//        this.authorityRepository.saveAll(authorities);
//
//        Authority developer = this.authorityRepository.findById(2L).orElse(null);
//
//        Set<User> users = new HashSet<>();
//        users.add(new User("sasho", PASSWORD_1234, true, new Date()));
//
//    }
}
