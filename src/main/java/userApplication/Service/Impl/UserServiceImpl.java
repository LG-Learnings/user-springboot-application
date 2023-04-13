package userApplication.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import userApplication.Dto.UserDto;
import userApplication.Entity.User;
import userApplication.Exception.EmailAlreadyExistsException;
import userApplication.Exception.ResourceNotFoundException;
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

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = userRepository.save(UserMapper.mapper.mapToUser(userDto));
        UserDto SavedUserDto = UserMapper.mapper.mapToUserDto(user);

        return SavedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        UserDto userDto = UserMapper.mapper.mapToUserDto(user);
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

        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);

        UserDto updatedUserDto = UserMapper.mapper.mapToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }
}
