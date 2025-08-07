package com.challenge.literalura.repositories;

import com.challenge.literalura.models.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LangRepository extends JpaRepository<Lang, Long> {
    Optional<Lang> findByLangCodeContainsIgnoreCase(String langCode);
}
