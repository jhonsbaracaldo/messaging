package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.PermissionDto;
import efinomina.message.efinomina.domain.services.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/permissions")
public class Permission {

    public Permission(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    private final PermissionService permissionService;

    @PostMapping("create")
    public ResponseEntity<PermissionDto> createPermission(@RequestBody PermissionDto dto) {
        PermissionDto created = permissionService.createPermission(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping ("list")
    public List<PermissionDto> listPermissions() {
       return permissionService.findAllPermission();
    }


    @PutMapping("update/{id}")
    public ResponseEntity<PermissionDto> updatePermission(@RequestBody PermissionDto dto ,@PathVariable Integer id) {
        PermissionDto updated = permissionService.UpdatePermission(id, dto);
        return ResponseEntity.ok(updated);
    }

     @DeleteMapping("delete/{id}")
    public Optional<PermissionDto> deletePermission(@PathVariable Integer id) {
        return permissionService.deletePermission(id);
    }



}
