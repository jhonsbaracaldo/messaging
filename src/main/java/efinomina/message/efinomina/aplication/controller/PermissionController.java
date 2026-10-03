package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.PermissionDto;
import efinomina.message.efinomina.domain.services.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    private final PermissionService permissionService;

    @PostMapping("create")
    public ResponseEntity<PermissionDto> createPermission(@RequestBody PermissionDto dto) {
        PermissionDto created = permissionService.createPermission(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping ("list")
    public ResponseEntity<List<PermissionDto>> listPermissions() {
        List<PermissionDto> permissions = permissionService.findAllPermission();

        if (permissions.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204
        }

        return ResponseEntity.ok(permissions); // HTTP 200
    }


    @PutMapping("update/{id}")
    public ResponseEntity<PermissionDto> updatePermission(@RequestBody PermissionDto dto ,@PathVariable Integer id) {
        PermissionDto updated = permissionService.UpdatePermission(id, dto);
        return ResponseEntity.ok(updated);
    }

     @DeleteMapping("delete/{id}")
    public ResponseEntity<PermissionDto> deletePermission(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }



}
