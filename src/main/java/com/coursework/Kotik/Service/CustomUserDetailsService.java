package com.coursework.Kotik.Service;

import com.coursework.Kotik.Repositories.ProductRepository;
import com.coursework.Kotik.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coursework.Kotik.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);

        if (user == null){
            LOG.error("User with username {} not found", username);
            throw new UsernameNotFoundException("User not found");
        }
        return build(user);
    }

    public User loadUserById(Long id) {
        return null;
    }

    public User findByUsername (String username){
        return userRepository.findByUsername(username);
    }

    public static User build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public void addPurchase(User user, Long product_id){
        user.getProductList().add(productRepository.findProductById(product_id));
        userRepository.save(user);
    }

    // ToDo Метод добавления пользователя в бд
}
