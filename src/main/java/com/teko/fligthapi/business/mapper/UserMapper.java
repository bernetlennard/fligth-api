package com.teko.fligthapi.business.mapper;

import com.teko.fligthapi.persistence.entity.User;
import com.teko.fligthapi.presentation.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User entity) {
        if (entity == null) return null;
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        // WICHTIG: Das Passwort mappen wir absichtlich nicht ins Rückgabe-DTO!
        return dto;
    }
}