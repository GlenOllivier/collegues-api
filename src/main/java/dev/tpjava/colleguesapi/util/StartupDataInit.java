package dev.tpjava.colleguesapi.util;

import dev.tpjava.colleguesapi.entity.Collegue;
import dev.tpjava.colleguesapi.service.CollegueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Component
public class StartupDataInit {
    @Autowired
    CollegueRepository collegueRepo;

    private final int NB_COLLEGUE = 100;


    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        Random r = new Random();
        for(int i = 0; i < NB_COLLEGUE; i++) {
            Collegue c = new Collegue();
            c.setMatricule(UUID.randomUUID().toString());
            c.setLastName(Constantes.NOMS.get(r.nextInt(Constantes.NOMS.size())));
            c.setFirstName(Constantes.PRENOMS.get(r.nextInt(Constantes.PRENOMS.size())));
            c.setEmail(c.getLastName()+ '.' + c.getFirstName() + "@gmail.com");
            c.setBirthDate(LocalDate.of(r.nextInt(50) + 1950,
                    r.nextInt(12) + 1,
                    r.nextInt(28) + 1));
            c.setPictureUrl("assets/images/keanu.png");

            collegueRepo.save(c);
        }
    }
}
