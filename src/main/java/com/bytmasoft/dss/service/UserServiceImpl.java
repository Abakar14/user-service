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

import java.util.List;
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

        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::entityToDto).orElseThrow(()-> new DSSEntityNotFoundException("User with username :"+username + " not found"))    ;
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
        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public UserDto markfordeletion(Long id) {
        User user = findUserById(id);
        user.setDeleted(true);
        user.setModifiedBy(authUtils.getUsername());
        return userMapper.entityToDto(userRepository.save(user));

    }

    @Override
    public UserDto add(UserCreateDto userCreateDto) {
        System.out.println(" user1 "+userCreateDto.toString());
        Role role = roleRepository.findById(1L).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+1L + " not found"));
        User user = userMapper.dtoCreateToEntity(userCreateDto);
        user.setAddedBy(authUtils.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDto(savedUser);
    }

    @Override
    public PagedModel<EntityModel<UserDto>> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserDto> dtos = userPage.map(userMapper::entityToDto);
        return pagedResourcesAssembler.toModel(dtos);
    }


    @Override
    public Page<UserDto> findAllByActiveStatus(boolean active, Pageable pageable) {
        return userRepository.findAll(UserSpecification.hasActive(active), pageable).map(userMapper::entityToDto);


    }

    @Override
    public List<UserDto> findAllAsList() {
        return userRepository.findAll().stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) throws DSSEntityNotFoundException {
    return userMapper.entityToDto(findUserById(id));
    }

    private User findUserById(Long id) {
       return userRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("User with id: "+id+" not found"));
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User userToUpdate = userMapper.dtoToEntity(findById(id));


        User userToSave = userMapper.partialUpdate(userUpdateDto, userToUpdate);
        return userMapper.entityToDto(userRepository.save(userToSave));
    }

    //TODO not delete the element only update the field is_for_delete = true
    @Override
    public UserDto delete(Long id) {
        User userToDelete = userMapper.dtoToEntity(findById(id));
        userRepository.delete(userToDelete);
        return userMapper.entityToDto(userToDelete);
    }
}