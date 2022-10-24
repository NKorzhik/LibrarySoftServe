package ua.softserve.mapper;

import ua.softserve.dto.UserDto;
import ua.softserve.model.User;

public class UserMapper {

    public static User mapToModel(UserDto userDto){
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .birthday(userDto.getBirthday())
                .registrationDate(userDto.getRegistrationDate())
                .role(userDto.getRole())
                .build();
    }

    public static UserDto mapToDto(User user){
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .birthday(user.getBirthday())
                .registrationDate(user.getRegistrationDate())
                .role(user.getRole())
                .build();
    }
}
