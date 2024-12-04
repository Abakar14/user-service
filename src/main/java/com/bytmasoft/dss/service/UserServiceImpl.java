package com.bytmasoft.dss.service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.UserCreateDto;
import com.bytmasoft.dss.dto.UserDto;
import com.bytmasoft.dss.dto.UserUpdateDto;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.entities.User;
import com.bytmasoft.dss.mapper.UserMapper;
import com.bytmasoft.dss.repository.PermissionRepository;
import com.bytmasoft.dss.repository.RoleRepository;
import com.bytmasoft.dss.repository.UserRepository;
import com.bytmasoft.dss.repository.UserSpecification;
import com.bytmasoft.dss.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PermissionRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final PagedResourcesAssembler<UserDto> pagedResourcesAssembler;
    private final AuthUtils authUtils;
private final RoleService roleService;


@Override
    public UserDto addRoleToUser(Long userId, Long roleId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new DSSEntityNotFoundException("User with id: "+userId+" not found")) ;
        Role role  = roleRepository.findById(roleId).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+roleId+" not found"));
        user.setRole(role);

        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toUserDto).orElseThrow(()-> new DSSEntityNotFoundException("User with username :"+username + " not found"))    ;
    }

    @Override
    public Long countAllUsers() {
        return userRepository.count();
    }

    @Override
    public Long countAllActiveUsers() {
        Specification<User> spec = UserSpecification.hasActive(true);
        return userRepository.count(spec);
    }

    @Override
    public Long countAllLockedUsers() {
        Specification<User> spec = UserSpecification.hasActive(false);
        return userRepository.count(spec);
    }

    @Override
    public UserDto unlock(Long id) {
        return null;
    }

    @Override
    public UserDto lockout(Long id) {
        User user = findUserById(id);

        user.setModifiedBy(authUtils.getUsername());
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto markfordeletion(Long id) {
        User user = findUserById(id);
        user.setDeleted(true);
        user.setModifiedBy(authUtils.getUsername());
        return userMapper.toUserDto(userRepository.save(user));

    }

    @Override
    public UserDto add(UserCreateDto userCreateDto) {
        User user = userMapper.dtoCreateToEntity(userCreateDto);
        user.setAddedBy(authUtils.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(userCreateDto.getRoleId()).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+1L + " not found")));

        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public PagedModel<EntityModel<UserDto>> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserDto> dtos = userPage.map(userMapper::toUserDto);
        return pagedResourcesAssembler.toModel(dtos);
    }


    @Override
    public Page<UserDto> findAllByActiveStatus(boolean active, Pageable pageable) {
        return userRepository.findAll(UserSpecification.hasActive(active), pageable).map(userMapper::toUserDto);


    }

    @Override
    public List<UserDto> findAllAsList() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) throws DSSEntityNotFoundException {
    return userMapper.toUserDto(findUserById(id));
    }

    private User findUserById(Long id) {
       return userRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("User with id: "+id+" not found"));
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User userToUpdate = findUserById(id);

        User userToSave = userMapper.partialUpdate(userUpdateDto, userToUpdate);
        userToSave.setModifiedBy(authUtils.getUsername());
        return userMapper.toUserDto(userRepository.save(userToSave));
    }

    //TODO not delete the element only update the field is_for_delete = true
    @Override
    public UserDto delete(Long id) {
        User userToDelete = userMapper.toUser(findById(id));
        userRepository.delete(userToDelete);
        return userMapper.toUserDto(userToDelete);
    }

public void processForgotPassword(String email) {
   /* User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User with email not found"));

    // Generate a password reset token
    String token = UUID.randomUUID().toString();
    PasswordResetToken resetToken = PasswordResetToken.builder()
                                            .token(token)
                                            .user(user)
                                            .expiryDate(LocalDateTime.now().plusHours(1))
                                            .build();

    tokenRepository.save(resetToken);

    // Send email with reset link
    String resetLink = "http://your-app-domain/reset-password?token=" + token;
    emailService.sendPasswordResetEmail(user.getEmail(), resetLink);*/
}
}
