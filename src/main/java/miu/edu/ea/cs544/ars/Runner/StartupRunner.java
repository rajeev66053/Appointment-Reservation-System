package miu.edu.ea.cs544.ars.Runner;

import miu.edu.ea.cs544.ars.domain.*;
import miu.edu.ea.cs544.ars.domain.enums.RoleType;
import miu.edu.ea.cs544.ars.repository.PersonRepositoryInterface;
import miu.edu.ea.cs544.ars.repository.RoleRepository;
import miu.edu.ea.cs544.ars.repository.SessionRepositoryInterface;
import miu.edu.ea.cs544.ars.service.AppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class StartupRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PersonRepositoryInterface personRepositoryInterface;
    private final SessionRepositoryInterface sessionRepositoryInterface;
    private final AppointmentService appointmentService;

    public StartupRunner(RoleRepository roleRepository,
                         PersonRepositoryInterface personRepositoryInterface,
                         SessionRepositoryInterface sessionRepositoryInterface,
                         AppointmentService appointmentService) {
        this.roleRepository = roleRepository;
        this.personRepositoryInterface = personRepositoryInterface;
        this.sessionRepositoryInterface = sessionRepositoryInterface;
        this.appointmentService = appointmentService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
    	
        Role adminRole = new Role(RoleType.ADMIN.toString());
        this.roleRepository.save(adminRole);
        Role customerRole = new Role(RoleType.CUSTOMER.toString());
        this.roleRepository.save(customerRole);
        Role counselorRole = new Role(RoleType.COUNSELOR.toString());
        this.roleRepository.save(counselorRole);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Person admin = new Person("admin", passwordEncoder.encode("admin"));
        admin.setFirstName("admin");
        admin.setLastName("K");
        admin.setDob(new Date());
        admin.setEmail("karsten.rivas@gmail.com");
        admin.addRole(adminRole);
        
        Person customer1 = new Person("customer1", passwordEncoder.encode("customer1"));
        customer1.setFirstName("customer1");
        customer1.setLastName("L1");
        customer1.setDob(new Date());
        customer1.setEmail("karsten.rivas@gmail.com");
        customer1.addRole(customerRole);
        
        Person customer2 = new Person("customer2", passwordEncoder.encode("customer2"));
        customer2.setFirstName("customer2");
        customer2.setLastName("L2");
        customer2.setDob(new Date());
        customer2.setEmail("rajeevthapaliya@gmail.com");
        customer2.addRole(customerRole);
        
        Person counselor1 = new Person("provider1", passwordEncoder.encode("provider1"));
        counselor1.setFirstName("provider1");
        counselor1.setLastName("L1");
        counselor1.setDob(new Date());
        counselor1.setEmail("krstnrivas@gmail.com");
        counselor1.addRole(counselorRole);
        
        Person counselor2 = new Person("provider2", passwordEncoder.encode("provider2"));
        counselor2.setFirstName("provider2");
        counselor2.setLastName("L2");
        counselor2.setDob(new Date());
        counselor2.setEmail("rthapaliya@miu.edu");
        counselor2.addRole(counselorRole);
        
        
        this.personRepositoryInterface.save(admin);
        this.personRepositoryInterface.save(customer1);
        this.personRepositoryInterface.save(counselor1);
        this.personRepositoryInterface.save(customer2);
        this.personRepositoryInterface.save(counselor2);
        
        
//        Session session = new Session("fairfield", 120, "2021-10-10", 1000);
//        session.setPerson(counselor);
//        this.sessionRepositoryInterface.save(session);


//        Appointment appointment = new Appointment(new Date(), new Date(), customer, session, AppointmentStatus.CREATED);
//        this.appointmentService.save(appointment);
    }
}