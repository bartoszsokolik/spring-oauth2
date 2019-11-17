package pl.solutions.software.sokolik.bartosz.oauth;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.solutions.software.sokolik.bartosz.oauth.user.domain.Authority;
import pl.solutions.software.sokolik.bartosz.oauth.user.domain.Role;
import pl.solutions.software.sokolik.bartosz.oauth.user.domain.User;
import pl.solutions.software.sokolik.bartosz.oauth.user.domain.UserRepository;

@SpringBootApplication
public class OauthApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Set<Authority> authorities = Set.of(new Authority("READ_ONLY"));

		Set<Role> roles = Set.of(new Role("ROLE_USER", authorities));

		User user = new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("user"));
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		user.setCredentialsExpired(false);
		user.setEnabled(true);
		user.setRoles(roles);
		userRepository.save(user);

		Set<Authority> authorities2 = Set.of(new Authority("READ_WRITE"));

		Set<Role> roles2 = Set.of(new Role("ROLE_ADMIN", authorities2));

		User user2 = new User();
		user2.setUsername("admin");
		user2.setPassword(passwordEncoder.encode("admin"));
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setEnabled(true);
		user2.setRoles(roles2);
		userRepository.save(user2);
	}
}
