package userApplication.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import userApplication.Dto.UserDto;
import userApplication.Entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    User mapToUser(UserDto userDto);
    UserDto mapToUserDto(User user);
    List<UserDto> mapListToUserDto(List<User> userList);
}
