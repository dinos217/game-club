package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.MemberDto;
import com.vromo.gameclub.dtos.MemberRequestDto;

public interface MemberService {

    MemberDto save(MemberRequestDto memberRequestDto);

    void delete(Long id);
}
