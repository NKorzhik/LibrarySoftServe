package ua.softserve.mapper;

import ua.softserve.dto.UserReadDto;
import ua.softserve.model.User;

public class UserReadMapper {
    public static User mapToModel(UserReadDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public static UserReadDto mapToDto(User user) {
        return UserReadDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
