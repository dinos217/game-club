package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.MemberDto;
import com.vromo.gameclub.dtos.MemberRequestDto;
import com.vromo.gameclub.entities.Member;
import com.vromo.gameclub.exceptions.ResourceNotFoundException;
import com.vromo.gameclub.mappers.MemberMapper;
import com.vromo.gameclub.repositories.MemberRepository;
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
