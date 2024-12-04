package com.bytmasoft.dss.service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.dto.RoleDetailsDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.dto.RoleUpdateDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository authorityRepository;
    private final AuthUtils authUtils;

    @Override
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

    @Override
    public PagedModel<EntityModel<RoleDto>> findAll(Pageable pageable) {
        return null;
    }


    @Override
    public Page<RoleDto> findAllByActiveStatus(boolean active, Pageable pageable) {
        return roleRepository.findAll(RoleSpecification.hasActive(active), pageable).map(roleMapper::toRoleDto);
    }

    @Override
    public List<RoleDto> findAllAsList() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleDto).collect(Collectors.toList());

    }

    @Override
    public RoleDto findById(Long id) throws DSSEntityNotFoundException {
        return roleMapper.toRoleDto(findRoleById(id));

    }
private Role findRoleById(Long id){
    return roleRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+id+" not found"));

}
    @Override
    public RoleDto update(Long id, RoleUpdateDto roleUpdateDto) {
        Role roleToUpdate = roleMapper.toRole(findById(id));
           Role roleToSave = roleMapper.partialUpdate(roleUpdateDto, roleToUpdate);
        return roleMapper.toRoleDto(roleRepository.save(roleToSave));
    }

    @Override
    public RoleDto delete(Long id) {
        Role roleToDelete = roleMapper.toRole(findById(id));
        roleRepository.delete(roleToDelete);
        return roleMapper.toRoleDto(roleToDelete);
    }


    @Override
    public RoleDto addAuthorityToRole(Long roleId, Long authorityId) {

        Role role =  roleRepository.findById(roleId).orElseThrow(()-> new DSSEntityNotFoundException("Role with id: "+roleId+" not found")) ;
        Permission authority =  authorityRepository.findById(authorityId).orElseThrow(()-> new DSSEntityNotFoundException("Authority with id: "+authorityId+" not found"));
        role.getPermissions().add(authority);


        return  roleMapper.toRoleDto(roleRepository.save(role));

    }

    @Override
    public int count() {
        return roleRepository.findAll().size();
    }

    @Override
    public Long countRoles() {
        return roleRepository.count();
    }

    @Override
    public Long countActiveRoles() {
        Specification<Role> spec = RoleSpecification.hasActive(true);
        return roleRepository.count(spec);
    }

    @Override
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
        return authorityRepository.count(PermissionSpecification.hasActive(true));
    }


    public Long countAllLocked() {
        return authorityRepository.count(PermissionSpecification.hasActive(false));
    }

    public RoleDto findRoleByName(String name) {
        return roleMapper.toRoleDto(roleRepository.findByName(name));
    }

public RoleDetailsDto findRoleDetailsById(Long id) {
    return roleMapper.toRoleDetails(findRoleById(id));
}

}
