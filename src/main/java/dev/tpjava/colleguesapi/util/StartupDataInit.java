package dev.tpjava.colleguesapi.util;

import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.entity.User;
import dev.tpjava.colleguesapi.service.CollegueRepository;
import dev.tpjava.colleguesapi.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class StartupDataInit {
    @Autowired
    private CollegueRepository collegueRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final int NB_COLLEGUE = 100;


    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        Random r = new Random();

        Map<String, Boolean> map = new HashMap<>();

        for(int i = 0; i < NB_COLLEGUE; i++) {
            Collegue c = new Collegue();
            c.setMatricule(UUID.randomUUID().toString());
            c.setLastName(Constantes.NOMS.get(r.nextInt(Constantes.NOMS.size())));
            c.setFirstName(Constantes.PRENOMS.get(r.nextInt(Constantes.PRENOMS.size())));
            c.setEmail(c.getLastName()+ '.' + c.getFirstName() + "@gmail.com");
            c.setBirthDate(LocalDate.of(r.nextInt(50) + 1950,
                    r.nextInt(12) + 1,
                    r.nextInt(28) + 1));
            c.setPictureUrl(Constantes.IMAGES.get(r.nextInt(Constantes.IMAGES.size())));

            if (!map.containsKey(c.getEmail())) {
                map.put(c.getEmail(), true);

                collegueRepo.save(c);

                User u = new User();
                u.setUsername(c.getEmail());
                u.setPassword(passwordEncoder.encode(c.getFirstName()));
                u.setRoles(Arrays.asList("ROLE_USER"));
                u.setCollegue(c);

                userRepository.save(u);
            }

        }

        User u = new User();
        u.setUsername("root");
        u.setPassword(passwordEncoder.encode("proot"));
        u.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));

        userRepository.save(u);

    }
}
