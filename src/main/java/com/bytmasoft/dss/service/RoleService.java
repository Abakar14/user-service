package com.bytmasoft.dss.service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.common.service.ServiceApi;
import com.bytmasoft.dss.dto.*;
import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.mapper.RoleMapper;
import com.bytmasoft.dss.repository.PermissionRepository;
import com.bytmasoft.dss.repository.PermissionSpecification;
import com.bytmasoft.dss.repository.RoleRepository;
import com.bytmasoft.dss.repository.RoleSpecification;
import com.bytmasoft.dss.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService implements ServiceApi<RoleDto, RoleCreateDto, RoleUpdateDto> {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;
    private final AuthUtils authUtils;



    public RoleDto add(RoleCreateDto roleCreateDto) {

        Role role = roleMapper.dtoCreateToEntity(roleCreateDto);
        if(roleCreateDto.getParent_role_id() != null && roleCreateDto.getParent_role_id() > 0) {
            Role parentRole = findRoleById(roleCreateDto.getParent_role_id());
            role.setParentRole(parentRole);
        }

        role.setAddedBy(authUtils.getUsername());
        role.setName("ROLE_"+role.getName().toUpperCase());
        Role savedRole = roleRepository.save(role);
        return roleMapper.toRoleDto(savedRole);
    }

    
    public PagedModel<EntityModel<RoleDto>> findAll(Pageable pageable) {
        return null;
    }


    
    public Page<RoleDto> findAllByActiveStatus(boolean active, Pageable pageable) {
        return roleRepository.findAll(RoleSpecification.hasActive(active), pageable).map(roleMapper::toRoleDto);
    }

    
    public List<RoleDto> findAllAsList() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleDto).collect(Collectors.toList());

    }

public List<RoleDetailsDto> findRoleWithSubRoles() {
    return roleRepository.findAll().stream().map(roleMapper::toRoleDetails).collect(Collectors.toList());

}



    
    public RoleDto findById(Long id) throws DSSEntityNotFoundException {
        return roleMapper.toRoleDto(findRoleById(id));

    }
private Role findRoleById(Long id){
    return roleRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+id+" not found"));

}
    
    public RoleDto update(Long id, RoleUpdateDto roleUpdateDto) {
        Role roleToUpdate = roleMapper.toRole(findById(id));
           Role roleToSave = roleMapper.partialUpdate(roleUpdateDto, roleToUpdate);
        return roleMapper.toRoleDto(roleRepository.save(roleToSave));
    }

    
    public RoleDto delete(Long id) {
        Role roleToDelete = roleMapper.toRole(findById(id));
        roleRepository.delete(roleToDelete);
        return roleMapper.toRoleDto(roleToDelete);
    }


    
    public RoleDto addPermissionToRole(Long roleId, Long permissionId) {

        Role role =  roleRepository.findById(roleId).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+roleId+" not found")) ;
        Permission permission =  permissionRepository.findById(permissionId).orElseThrow(()-> new DSSEntityNotFoundException("Authority with id: "+permissionId+" not found"));
        role.getPermissions().add(permission);


        return  roleMapper.toRoleDto(roleRepository.save(role));

    }

    
    public int count() {
        return roleRepository.findAll().size();
    }

    
    public Long countRoles() {
        return roleRepository.count();
    }

    
    public Long countActiveRoles() {
        Specification<Role> spec = RoleSpecification.hasActive(true);
        return roleRepository.count(spec);
    }

    
    public Long countLockedRoles() {
        Specification<Role> spec = RoleSpecification.hasActive(false);
        return roleRepository.count(spec);
    }


    public RoleDto unlock(Long id) {
        Role role = findRoleById(id);
        role.setIsActive(true);
        role.setModifiedBy(authUtils.getUsername());
        return roleMapper.toRoleDto(roleRepository.save(role));
    }


    public RoleDto lockout(Long id) {
        Role role = findRoleById(id);
        role.setIsActive(false);
        role.setModifiedBy(authUtils.getUsername());
        return roleMapper.toRoleDto(roleRepository.save(role));

    }


    public RoleDto markfordeletion(Long id) {
        Role role = findRoleById(id);
        role.setDeleted(true);
        role.setModifiedBy(authUtils.getUsername());
        return roleMapper.toRoleDto(roleRepository.save(role));
    }


    public Long countAllActives() {
        return permissionRepository.count(PermissionSpecification.hasActive(true));
    }


    public Long countAllLocked() {
        return permissionRepository.count(PermissionSpecification.hasActive(false));
    }

   @Transactional(readOnly = true)
    public RoleDto findRoleByName(String name) {
        return roleMapper.toRoleDto(roleRepository.findByName(name));
    }

public RoleDetailsDto findRoleDetailsById(Long id) {
    return roleMapper.toRoleDetails(findRoleById(id));
}

public RoleDto addPermissionsToRole(Long roleId, List<Long> permissionIds) {
    Role role = findRoleById(roleId);
    permissionIds.forEach(permissionId -> {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(()-> new DSSEntityNotFoundException("Authority with id: "+permissionId+" not found"));
        role.getPermissions().add(permission);
    });
    return  roleMapper.toRoleDto(roleRepository.save(role));


}


public List<RoleDetailsDto> findDetailsRoles() {
    return roleRepository.findAll().stream().map(roleMapper::toRoleDetails).collect(Collectors.toList());

}
}
