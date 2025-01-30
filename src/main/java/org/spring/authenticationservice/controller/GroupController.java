package org.spring.authenticationservice.controller;

import org.spring.authenticationservice.DTO.Group.GroupDto;
import org.spring.authenticationservice.Service.GroupService.GroupService;
import org.spring.authenticationservice.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.spring.authenticationservice.Utils.SecurityUtils.getUsername;

@RestController
@RequestMapping("/grp")
public class GroupController {


    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody GroupDto groupDto){
        try{
            Group newGroup = groupService.createGroup(groupDto);
            return ResponseEntity.status(200).body(newGroup.getId().toString());
        }
        catch (Exception e){

            return ResponseEntity.status(500).body(e.getMessage());

        }

    }



}
