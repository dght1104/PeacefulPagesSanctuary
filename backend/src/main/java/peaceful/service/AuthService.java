package peaceful.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaceful.config.JwtUtil;
import peaceful.dto.LoginRequest;
import peaceful.dto.RegisterRequest;
import peaceful.model.Customer;
import peaceful.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (customerRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(request.getPassword())
                .verified(false)
                .active(true)
                .customerGroup("Silver")
                .build();

        customerRepository.save(customer);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        Customer customer = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!customer.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(customer.getUsername());
    }
}
