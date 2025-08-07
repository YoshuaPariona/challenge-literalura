package com.challenge.literalura.services;

import com.challenge.literalura.models.Lang;
import com.challenge.literalura.repositories.LangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LangService {

    @Autowired
    private LangRepository langRepository;

    public Lang checkDuplicateLang(String langCode) {
        return langRepository.findByLangCodeContainsIgnoreCase(langCode)
                .orElseGet(() -> langRepository.save(new Lang(langCode)));
    }
}
