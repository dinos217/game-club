package com.project.gameclub.controllers;

import com.project.gameclub.dtos.MemberRequestDto;
import com.project.gameclub.dtos.MemberDto;
import com.project.gameclub.services.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> save(@RequestBody MemberRequestDto memberRequestDto) {

        logger.info("Started saving new member...");

        MemberDto memberDto = memberService.save(memberRequestDto);

        logger.info("Member with email: " + memberRequestDto.getEmail() + " was saved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> update(@PathVariable Long id,
                                            @RequestBody MemberRequestDto memberRequestDto) {

        logger.info("Started updating a member...");

        MemberDto memberDto = memberService.update(id, memberRequestDto);

        logger.info("Member with id: " + id + " was updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        logger.info("Started deleting a member...");

        memberService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
