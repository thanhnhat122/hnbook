package hcmute.edu.hnlbook.service.impl;

import hcmute.edu.hnlbook.SampleData;
import hcmute.edu.hnlbook.model.User;
import hcmute.edu.hnlbook.repository.UserRepository;
import hcmute.edu.hnlbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static hcmute.edu.hnlbook.model.User.RoleEnum.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(SampleData.class);
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;
  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findById(email);
  }

  @Override
  public boolean existByPhone(String phone) {
    return userRepository.findByPhone(phone) != null;
  }

  @Override
  public Object insertUser(User newUser) {
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    return userRepository.save(newUser);
  }

  @Override
  public User updateUser(User newUser, String email) {
    return userRepository.findById(email)
        .map(user -> {
          user.setEmail(email);
          user.setFirstName(newUser.getFirstName());
          user.setLastName(newUser.getLastName());
          user.setAddress(newUser.getAddress());
          user.setProvince(newUser.getProvince());
          user.setPhone(newUser.getPhone());
          user.setPassword(passwordEncoder.encode(newUser.getPassword()));
          user.setRole(newUser.getRole());
          return userRepository.save(user);
        }).orElseGet(() -> {
          newUser.setEmail(email);
          return userRepository.save(newUser);
        });
  }

  @Override
  public boolean existById(String email) {
    return userRepository.existsById(email);
  }

  @Override
  public void deleteUser(String email) {
    userRepository.deleteById(email);
  }

  @Override
  public User findUserByEmail(String username) {
    return userRepository.findByEmail(username);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User signupUser(User newUser) {
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    newUser.setRole(ROLE_KH);
    return userRepository.save(newUser);
  }

  @Override
  public List<User> getAllAdmin() {
    return userRepository.findByRole(ROLE_AD);
  }

  @Override
  public List<User> getAllStaff() {
    return userRepository.findByRole(ROLE_NV);
  }

  @Override
  public List<User> getAllCustomer() {
    return userRepository.findByRole(ROLE_KH);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user==null) {
      throw new UsernameNotFoundException("User not found in the database");
    } else {
      logger.info("User found in the database: {}", username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
  }

  @Override
  public boolean isMatchPassword(String password, String userPassword) {
    return passwordEncoder.matches(password, userPassword);
  }

  @Override
  public User updatePassword(User user, String newPassword) {
    user.setPassword(passwordEncoder.encode(newPassword));
    return userRepository.save(user);
  }
}
