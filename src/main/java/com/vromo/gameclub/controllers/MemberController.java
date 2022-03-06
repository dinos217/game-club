package com.vromo.gameclub.controllers;

import com.vromo.gameclub.dtos.MemberDto;
import com.vromo.gameclub.dtos.MemberRequestDto;
import com.vromo.gameclub.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> save(@RequestBody MemberRequestDto memberRequestDto) {

        MemberDto memberDto = memberService.save(memberRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @DeleteMapping
    public ResponseEntity delete(Long id) {

        memberService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
