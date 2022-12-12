package com.example.neighbor.infrastructure.mappers;


import com.example.neighbor.models.Role;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    @EnumMapping()
    default int roleToInt(Role role){
        return role.ordinal();
    }
}
