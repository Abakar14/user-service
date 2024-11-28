package com.bytmasoft.dss.controller;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.RoleCreateDto;
import com.bytmasoft.dss.dto.RoleDto;
import com.bytmasoft.dss.dto.RoleUpdateDto;
import com.bytmasoft.dss.repository.RoleRepository;
import com.bytmasoft.dss.service.RoleServiceImpl;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Validated
@AllArgsConstructor
@Schema(name = "Role")
@Tag(name = "Role", description = "The Role Controller  used for add Role to user")
@RequestMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RoleController implements  RoleApi {

    private final RoleServiceImpl roleService;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<RoleDto> save(@Valid RoleCreateDto roleCreateDto) {
        System.out.println("Role : "+roleCreateDto.toString());
        return ResponseEntity.ok(roleService.add(roleCreateDto));
    }


    @Override
    public List<RoleDto> findList() {
        return  roleService.findAllAsList();
    }


    @Override
    public ResponseEntity<RoleDto> findById(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @Override
    public ResponseEntity<RoleDto> update(Long id, @Valid RoleUpdateDto roleUpdateDto) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(roleService.update(id, roleUpdateDto));
    }

    @Override
    public ResponseEntity<RoleDto> delete(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(roleService.delete(id));
    }

    @Override
    public ResponseEntity<RoleDto> addAuthorityToRole(Long role_id, Long authority_id) {
        return ResponseEntity.ok(roleService.addAuthorityToRole(role_id, authority_id));
    }

   /* @Override
    public Long countAllRoles() {
        return roleService.countRoles();
    }

    @Override
    public Long countAllActiveRoles() {
        return roleService.countActiveRoles();
    }

    @Override
    public Long countAllLockedRoles() {
        return roleService.countLockedRoles();
    }*/

    @Override
    public ResponseEntity<RoleDto> unlock(Long aLong) throws DSSEntityNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<RoleDto> lockout(Long aLong) throws DSSEntityNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<RoleDto> markfordeletion(Long aLong) throws DSSEntityNotFoundException {
        return null;
    }

    @Override
    public Long countAll() {
        return roleRepository.count();
    }

    @Override
    public Long countAllActives() {
        return roleService.countActiveRoles();
    }

    @Override
    public Long countAllLocked() {
        return roleService.countLockedRoles();
    }

}
