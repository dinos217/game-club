package com.project.gameclub.mappers;

import com.project.gameclub.dtos.MemberDto;
import com.project.gameclub.dtos.MemberRequestDto;
import com.project.gameclub.entities.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {

    MemberDto memberToMemberDto(Member member);
    Member memberRequestDtoToMember(MemberRequestDto memberRequestDto);
}
