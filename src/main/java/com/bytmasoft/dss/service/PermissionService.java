package com.bytmasoft.dss.service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.common.service.ServiceApi;
import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.mapper.PermissionMapper;
import com.bytmasoft.dss.mapper.RoleMapper;
import com.bytmasoft.dss.repository.PermissionRepository;
import com.bytmasoft.dss.repository.PermissionSpecification;
import com.bytmasoft.dss.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PermissionService implements ServiceApi<PermissionDto, PermissionCreateDto, PermissionUpdateDto> {


    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;
    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final AuthUtils authUtils;

    
    public PermissionDto add(PermissionCreateDto permissionCreateDto) {
        Permission permission = permissionMapper.dtoCreateToEntity(permissionCreateDto);
        permission.setName(permission.getName().toUpperCase());
        permission.setAddedBy(authUtils.getUsername());
        Permission savedPermission = permissionRepository.save(permission);
        return permissionMapper.toPermissionDto(savedPermission);
    }

    
    public PagedModel<EntityModel<PermissionDto>> findAll(Pageable pageable) {
        return null;
    }


    
    public Page<PermissionDto> findAllByActiveStatus(boolean active, Pageable pageable) {
        return permissionRepository.findAll(PermissionSpecification.hasActive(active), pageable).map(permissionMapper::toPermissionDto);

    }

    
    public List<PermissionDto> findAllAsList() {
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionDto).collect(Collectors.toList());

    }

    
    public PermissionDto findById(Long id) throws DSSEntityNotFoundException {
        return permissionMapper.toPermissionDto(findAuthorityById(id));
    }

    private Permission findAuthorityById(Long id) throws DSSEntityNotFoundException {
        return permissionRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("Group with id: "+id+" not found"));

    }

    
    public PermissionDto update(Long id, PermissionUpdateDto groupUpdateDto) {
        Permission groupToUpdate = permissionMapper.toPermission(findById(id));

        Permission roleToSave = permissionMapper.partialUpdate(groupUpdateDto, groupToUpdate);
        return permissionMapper.toPermissionDto(permissionRepository.save(roleToSave));
    }

    //TODO not delete the element only update the field is_for_delete = true
    
    public PermissionDto delete(Long id) {
        Permission groupToDelete = permissionMapper.toPermission(findById(id));
        permissionRepository.delete(groupToDelete);
        return permissionMapper.toPermissionDto(groupToDelete);
    }

    
    public PermissionDto addPermissionToRole(Long groupId, Long roleId) {
        Permission authority = permissionMapper.toPermission(findById(groupId));
        Role role = roleMapper.toRole(roleService.findById(roleId));
//        authority.getRoles().add(role);

       return permissionMapper.toPermissionDto(permissionRepository.save(authority));

    }

    
    public PermissionDto addPermissionToRole(Long groupId, RoleCreateDto roleCreateDto) {
        Permission authority = permissionMapper.toPermission(findById(groupId));
        Role role = roleMapper.toRole(roleService.add(roleCreateDto));

//        authority.getRoles().add(role);

        return permissionMapper.toPermissionDto(permissionRepository.save(authority));
    }

    
    public Long countActiveAuthorities() {
        Specification<Permission> spec = PermissionSpecification.hasActive(true);
        return permissionRepository.count(spec);
    }

    
    public Long countPermissions() {
        return permissionRepository.count();
    }

    
    public Long countLockedAuthorities() {
        Specification<Permission> spec = PermissionSpecification.hasActive(false);
        return permissionRepository.count(spec);
    }

    
    public PermissionDto unlock(Long id) {
        Permission authority = findAuthorityById(id);
        authority.setIsActive(true);
        authority.setModifiedBy(authUtils.getUsername());
        return permissionMapper.toPermissionDto(permissionRepository.save(authority));
    }

    
    public PermissionDto lockout(Long id) {
        Permission authority = findAuthorityById(id);
        authority.setIsActive(false);
        authority.setModifiedBy(authUtils.getUsername());
        return permissionMapper.toPermissionDto(permissionRepository.save(authority));

    }

    
    public PermissionDto markfordeletion(Long id) {
        Permission authority = findAuthorityById(id);
         authority.setDeleted(false);
         authority.setModifiedBy(authUtils.getUsername());
         return permissionMapper.toPermissionDto(permissionRepository.save(authority));
    }

    
    public Long countAllActives() {
        return permissionRepository.count(PermissionSpecification.hasActive(true));
    }

    
    public Long countAllLocked() {
        return permissionRepository.count(PermissionSpecification.hasActive(false));
    }


}
