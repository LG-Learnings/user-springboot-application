package userApplication.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userApplication.Dto.UserDto;
import userApplication.Entity.User;
import userApplication.Mapper.UserMapper;
import userApplication.Repository.UserRepository;
import userApplication.Service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = userRepository.save(UserMapper.mapper.mapToUser(userDto));
        UserDto SavedUserDto = UserMapper.mapper.mapToUserDto(user);

        return SavedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        UserDto userDto = UserMapper.mapper.mapToUserDto(optionalUser.get());
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = UserMapper.mapper.mapListToUserDto(userList);
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User existingUser = userRepository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);

        UserDto updatedUserDto = UserMapper.mapper.mapToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
