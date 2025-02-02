package org.spring.authenticationservice.controller;

import org.spring.authenticationservice.DTO.Group.CreateGroupDto;
import org.spring.authenticationservice.DTO.Group.RespGroupDto;
import org.spring.authenticationservice.Service.GroupService.GroupService;
import org.spring.authenticationservice.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grp")
public class GroupController {


    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody CreateGroupDto groupDto){
        try{
            Group newGroup = groupService.createGroup(groupDto);
            return ResponseEntity.status(200).body(newGroup.getId().toString());
        }
        catch (Exception e){

            return ResponseEntity.status(500).body(e.getMessage());

        }

    }

    @PostMapping("/{groupId}/invite/{userId}")
    public ResponseEntity<String> inviteUser(@PathVariable Long groupId, @PathVariable Long userId){
        try{
            String message = groupService.inviteUserToGroup(groupId,userId);
            return ResponseEntity.status(200).body(message);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{groupId}/respond")
    public ResponseEntity<String> respondToGroup(@PathVariable Long groupId,@RequestParam Long userId, @RequestParam Boolean approve){
        try{

            String message = groupService.respondToInvitation(userId,groupId,approve);
            return ResponseEntity.ok(message);

        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    @GetMapping("/group/${id}")
//    public ResponseEntity<RespGroupDto> getGroup(@PathVariable Long id){
//        try{
//
//            RespGroupDto respGroupDto = groupService.findGroupById(id);
//            return  ResponseEntity.status(200).body(
//                    respGroupDto
//            );
//        }
//        catch (Exception e){
//            return ResponseEntity
//        }
//    }



}
