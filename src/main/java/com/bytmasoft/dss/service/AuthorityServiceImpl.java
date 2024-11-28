package com.bytmasoft.dss.service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.entities.Permission;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.mapper.AuthorityMapper;
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
public class AuthorityServiceImpl implements AuthorityService {


    private final PermissionRepository authorityRepository;
    private final AuthorityMapper authorityMapper;
    private final RoleMapper roleMapper;
    private final RoleServiceImpl roleService;
    private final AuthUtils authUtils;

    @Override
    public PermissionDto add(PermissionCreateDto groupCreateDto) {
        Permission authority = authorityMapper.dtoCreateToEntity(groupCreateDto);
        authority.setName(authority.getName().toUpperCase());
        Permission savedUser = authorityRepository.save(authority);
        return authorityMapper.entityToDto(savedUser);
    }

    @Override
    public PagedModel<EntityModel<PermissionDto>> findAll(Pageable pageable) {
        return null;
    }



/*    @Override
    public Page<AuthorityDto> findAll(Pageable pageable) {
        return authorityRepository.findAll(pageable).map(authorityMapper::entityToDto);
    }*/

    @Override
    public Page<PermissionDto> findAllByActiveStatus(boolean active, Pageable pageable) {
        return authorityRepository.findAll(PermissionSpecification.hasActive(active), pageable).map(authorityMapper::entityToDto);

    }

    @Override
    public List<PermissionDto> findAllAsList() {
        return authorityRepository.findAll().stream().map(authorityMapper::entityToDto).collect(Collectors.toList());

    }

    @Override
    public PermissionDto findById(Long id) throws DSSEntityNotFoundException {
        return authorityMapper.entityToDto(findAuthorityById(id));
    }

    private Permission findAuthorityById(Long id) throws DSSEntityNotFoundException {
        return authorityRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("Group with id: "+id+" not found"));

    }

    @Override
    public PermissionDto update(Long id, PermissionUpdateDto groupUpdateDto) {
        Permission groupToUpdate = authorityMapper.dtoToEntity(findById(id));

        Permission roleToSave = authorityMapper.partialUpdate(groupUpdateDto, groupToUpdate);
        return authorityMapper.entityToDto(authorityRepository.save(roleToSave));
    }

    //TODO not delete the element only update the field is_for_delete = true
    @Override
    public PermissionDto delete(Long id) {
        Permission groupToDelete = authorityMapper.dtoToEntity(findById(id));
        authorityRepository.delete(groupToDelete);
        return authorityMapper.entityToDto(groupToDelete);
    }

    @Override
    public PermissionDto addAutorityToRole(Long groupId, Long roleId) {
        Permission authority = authorityMapper.dtoToEntity(findById(groupId));
        Role role = roleMapper.dtoToEntity(roleService.findById(roleId));
//        authority.getRoles().add(role);

       return authorityMapper.entityToDto(authorityRepository.save(authority));

    }

    @Override
    public PermissionDto addAutorityToRole(Long groupId, RoleCreateDto roleCreateDto) {
        Permission authority = authorityMapper.dtoToEntity(findById(groupId));
        Role role = roleMapper.dtoToEntity(roleService.add(roleCreateDto));

//        authority.getRoles().add(role);

        return authorityMapper.entityToDto(authorityRepository.save(authority));
    }

    @Override
    public Long countActiveAuthorities() {
        Specification<Permission> spec = PermissionSpecification.hasActive(true);
        return authorityRepository.count(spec);
    }

    @Override
    public Long countAuthorities() {
        return authorityRepository.count();
    }

    @Override
    public Long countLockedAuthorities() {
        Specification<Permission> spec = PermissionSpecification.hasActive(false);
        return authorityRepository.count(spec);
    }

    @Override
    public PermissionDto unlock(Long id) {
        Permission authority = findAuthorityById(id);
        authority.setIsActive(true);
        authority.setModifiedBy(authUtils.getUsername());
        return authorityMapper.entityToDto(authorityRepository.save(authority));
    }

    @Override
    public PermissionDto lockout(Long id) {
        Permission authority = findAuthorityById(id);
        authority.setIsActive(false);
        authority.setModifiedBy(authUtils.getUsername());
        return authorityMapper.entityToDto(authorityRepository.save(authority));

    }

    @Override
    public PermissionDto markfordeletion(Long id) {
        Permission authority = findAuthorityById(id);
         authority.setDeleted(false);
         authority.setModifiedBy(authUtils.getUsername());
         return authorityMapper.entityToDto(authorityRepository.save(authority));
    }

    @Override
    public Long countAllActives() {
        return authorityRepository.count(PermissionSpecification.hasActive(true));
    }

    @Override
    public Long countAllLocked() {
        return authorityRepository.count(PermissionSpecification.hasActive(false));
    }


}
