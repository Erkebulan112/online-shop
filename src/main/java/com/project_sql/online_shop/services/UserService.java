package com.project_sql.online_shop.services;

import com.project_sql.online_shop.dtos.UserDto;
import com.project_sql.online_shop.entities.User;
import com.project_sql.online_shop.mappers.UserMapper;
import com.project_sql.online_shop.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<UserDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(userMapper::toDto)
        .toList();
  }

  public UserDto getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    return userMapper.toDto(user);
  }

  @Transactional
  public UserDto addUser(UserDto userDto) {
    User user = userMapper.toEntity(userDto);
    User newUser = userRepository.save(user);
    log.info("New user: " + newUser);
    return userMapper.toDto(newUser);
  }

  @Transactional
  public UserDto updateUser(Long id, UserDto userDto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    user.setUsername(userDto.getUsername());
    user.setLogin(userDto.getLogin());
    user.setPassword(userDto.getPassword());
    return userMapper.toDto(userRepository.save(user));
  }

  @Transactional
  public void deleteUser(Long id) {
    log.info("Deleting user with id " + id);
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      log.info("Deleted user with id " + id);
    } else
      throw new EntityNotFoundException("User not found " + id);
  }

  public boolean authenticate(String login, String password) {
    return userRepository.findByLogin(login)
        .map(user -> user.getPassword().equals(password))
        .orElse(false);
  }
}

