package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.softserve.dao.UserDao;
import ua.softserve.dto.UserCreateDto;
import ua.softserve.mapper.UserCreateMapper;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.User;
import ua.softserve.model.enums.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserCreateDto userCreateDto){
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userCreateDto.setRegistrationDate(LocalDate.now());
        userCreateDto.setRole(Role.READER);
        User user = UserCreateMapper.mapToModel(userCreateDto);
        userDao.addUser(user);
    }


    public List<HistoryOfRequest> getRequestedBooks(long id){
        return userDao.getRequestedBooks(id);
    }

    public Optional<User> findUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public HistoryOfRequest getRequestById(long id){
        return userDao.getRequestById(id);
    }

//    public List<HistoryOfRequest> searchBook(String title){
//        return userDao.findBook(title);
//    }
}
