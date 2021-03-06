package com.project.gameclub.services;

import com.project.gameclub.dtos.MemberDto;
import com.project.gameclub.dtos.MemberRequestDto;

public interface MemberService {

    MemberDto save(MemberRequestDto memberRequestDto);

    MemberDto update(Long id, MemberRequestDto memberRequestDto);

    void delete(Long id);
}
