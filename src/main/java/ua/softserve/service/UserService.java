package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.softserve.dao.UserDao;
import ua.softserve.dto.UserDto;
import ua.softserve.mapper.UserMapper;
import ua.softserve.model.User;
import ua.softserve.model.enums.Role;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRegistrationDate(LocalDate.now());
        userDto.setRole(Role.READER);
        User user = UserMapper.mapToModel(userDto);
        userDao.addUser(user);
    }


}
