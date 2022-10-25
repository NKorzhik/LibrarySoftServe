package ua.softserve.mapper;

import ua.softserve.dto.UserCreateDto;
import ua.softserve.model.User;

public class UserCreateMapper {

    public static User mapToModel(UserCreateDto userDto){
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

    public static UserCreateDto mapToDto(User user){
        return UserCreateDto.builder()
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
