package com.project.gameclub.services;

import com.project.gameclub.dtos.MemberRequestDto;
import com.project.gameclub.entities.Member;
import com.project.gameclub.exceptions.InvalidRequestException;
import com.project.gameclub.exceptions.ResourceNotFoundException;
import com.project.gameclub.mappers.MemberMapper;
import com.project.gameclub.repositories.MemberRepository;
import com.project.gameclub.dtos.MemberDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDto save(MemberRequestDto memberRequestDto) {

        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            throw new InvalidRequestException("Member with email: "
                    + memberRequestDto.getEmail() + " already exists.");
        }

        Member member = memberMapper.memberRequestDtoToMember(memberRequestDto);
        return memberMapper.memberToMemberDto(memberRepository.save(member));
    }

    @Override
    public void delete(Long id) {

        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isEmpty()) {
            throw new ResourceNotFoundException("Could not find member with id: " + id);
        }
        memberRepository.delete(memberOptional.get());
    }
}