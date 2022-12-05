package com.ilgamumchu.demar.service;

import com.ilgamumchu.demar.domain.Diary;
import com.ilgamumchu.demar.domain.User;
import com.ilgamumchu.demar.dto.DiaryRequestDTO;
import com.ilgamumchu.demar.dto.DiaryResponseDTO;


import java.util.List;

public interface DiaryService {

    Long save(DiaryRequestDTO diaryDTO);

    List<DiaryResponseDTO> findAllByUserId(User user);

    DiaryResponseDTO findById(Long id);

    void deleteById(Long id);
}