package com.bytmasoft.dss.controller;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.PermissionCreateDto;
import com.bytmasoft.dss.dto.PermissionDto;
import com.bytmasoft.dss.dto.PermissionUpdateDto;
import com.bytmasoft.dss.service.AuthorityServiceImpl;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin


@Validated
@AllArgsConstructor
@Schema(name = "Permission")
@Tag(name = "Permission", description = "The Permission Service used to manage the Permissions")
@RequestMapping(value = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PersmissionController implements PermissionApi {

    private final AuthorityServiceImpl authorityService;

    @Override
    public ResponseEntity<PermissionDto> save(@Valid PermissionCreateDto authorityCreateDto) {
        return ResponseEntity.ok(authorityService.add(authorityCreateDto));
    }

/*
   public PagedModel<EntityModel<AuthorityDto>> findAll(Pageable pageable) {
        return null;
    }*/

/*    @Override
    public Page<AuthorityDto> findAll(Pageable pageable) {
        return authorityService.findAll(pageable);
    }*/

    @Override
    public List<PermissionDto> findList() {
        return authorityService.findAllAsList();
    }

    @Override
    public ResponseEntity<PermissionDto> findById(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(authorityService.findById(id));
    }

    @Override
    public ResponseEntity<PermissionDto> update(Long id, @Valid PermissionUpdateDto authorityUpdateDto) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(authorityService.update(id, authorityUpdateDto));
    }

    @Override
    public ResponseEntity<PermissionDto> delete(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(authorityService.delete(id));
    }

    @Override
    public ResponseEntity<PermissionDto> addAuthorityToRole(Long authority_id, Long role_id) {
        return ResponseEntity.ok(authorityService.addAutorityToRole(authority_id, role_id));
    }

  /*  @Override
    public Long countAuthorities() {
        return authorityService.countAuthorities();
    }*/

/*    @Override
    public Long countActiveAuthorities() {
        return authorityService.countActiveAuthorities();
    }*/

 /*   @Override
    public Long countLockedAuthorities() {
        return authorityService.countLockedAuthorities();
    }*/

    @Override
    public ResponseEntity<PermissionDto> unlock(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(authorityService.unlock(id));
    }

    @Override
    public ResponseEntity<PermissionDto> lockout(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(authorityService.lockout(id));
    }

    @Override
    public ResponseEntity<PermissionDto> markfordeletion(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(authorityService.markfordeletion(id));
    }

    @Override
    public Long countAll() {
        return authorityService.countAuthorities();
    }

    @Override
    public Long countAllActives() {
        return authorityService.countAllActives();
    }

    @Override
    public Long countAllLocked() {
        return authorityService.countAllLocked();
    }


}



