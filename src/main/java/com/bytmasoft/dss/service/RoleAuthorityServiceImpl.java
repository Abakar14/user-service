package com.bytmasoft.dss.service;


import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.RoleAuthorityCreateDto;
import com.bytmasoft.dss.dto.RoleAuthorityDto;
import com.bytmasoft.dss.dto.RoleAuthorityUpdateDto;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.entities.RoleAuthority;
import com.bytmasoft.dss.mapper.RoleAutorityMapper;
import com.bytmasoft.dss.repository.RoleAuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleAuthorityServiceImpl implements RoleAuthorityService {


private final RoleAuthorityRepository roleAuthorityRepository;
private final RoleAutorityMapper roleAutorityMapper;

@Override
public RoleAuthorityDto add(RoleAuthorityCreateDto roleAuthorityCreateDto) {

	RoleAuthority roleAuthority  = roleAutorityMapper.dtoCreateToEntity(roleAuthorityCreateDto);

/*	role.setAddedOn(roleRepository.getCurrentTimestamp());
	role.setModifiedOn(roleRepository.getCurrentTimestamp());
	role.setName("ROLE_"+role.getName().toUpperCase());
	Role savedRole = roleRepository.save(role);
	return roleMapper.entityToDto(savedRole);*/
return null;
}

@Override
public PagedModel<EntityModel<RoleAuthorityDto>> findAll(Pageable pageable) {
	return null;
}

@Override
public Page<RoleAuthorityDto> findAllByActiveStatus(boolean b, Pageable pageable) {
	return null;
}

@Override
public List<RoleAuthorityDto> findAllAsList() {
	return List.of();
}

@Override
public RoleAuthorityDto findById(Long aLong) throws DSSEntityNotFoundException {
	return null;
}

@Override
public RoleAuthorityDto update(Long aLong, RoleAuthorityUpdateDto roleAuthorityUpdateDto) {
	return null;
}

@Override
public RoleAuthorityDto delete(Long aLong) {
	return null;
}
}
