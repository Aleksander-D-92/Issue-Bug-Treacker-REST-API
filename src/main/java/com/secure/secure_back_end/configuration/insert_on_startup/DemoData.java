package com.secure.secure_back_end.configuration.insert_on_startup;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DemoData
{

    private final AuthorityRepository authorityRepository;

    @Autowired
    public DemoData(AuthorityRepository authorityRepository)
    {
        this.authorityRepository = authorityRepository;
    }

//    @EventListener
//    public void appReady(ApplicationReadyEvent event)
//    {
//        Authority authority1 = new Authority();
//        authority1.setAuthority("ROLE_USER");
//        Authority authority2 = new Authority();
//        authority2.setAuthority("ROLE_ADMIN");
//        this.authorityRepository.save(authority1);
//        this.authorityRepository.save(authority2);
//    }
}
