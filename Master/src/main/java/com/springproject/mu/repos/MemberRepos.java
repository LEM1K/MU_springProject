package com.springproject.mu.repos;

import com.springproject.mu.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepos extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
