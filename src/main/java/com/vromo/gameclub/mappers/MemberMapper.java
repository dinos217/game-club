package com.vromo.gameclub.mappers;

import com.vromo.gameclub.dtos.MemberDto;
import com.vromo.gameclub.dtos.MemberRequestDto;
import com.vromo.gameclub.entities.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {

    MemberDto memberToMemberDto(Member member);
    Member memberRequestDtoToMember(MemberRequestDto memberRequestDto);
}
