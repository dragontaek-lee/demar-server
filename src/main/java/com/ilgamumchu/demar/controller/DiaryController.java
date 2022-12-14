package com.ilgamumchu.demar.controller;

import com.ilgamumchu.demar.domain.User;
import com.ilgamumchu.demar.dto.DiaryRequestDTO;
import com.ilgamumchu.demar.dto.DiaryResponseDTO;
import com.ilgamumchu.demar.dto.MusicResponseDTO;
import com.ilgamumchu.demar.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import com.ilgamumchu.demar.security.jwt.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(value = "diary")
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/write")
    public @ResponseBody Long diaryWrite(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid DiaryRequestDTO diaryDTO) throws Exception {
        User user = userDetails.getUser();
        diaryDTO.setUserId(user);
        return diaryService.save(diaryDTO);
    }

    @GetMapping(value = "/")
    public List<DiaryResponseDTO> getUserDiaryList(@AuthenticationPrincipal UserDetailsImpl userDetails){
       User user = userDetails.getUser();
       return diaryService.findAllByUserId(user);
    }

    @GetMapping(value = "/{diaryId}")
    public DiaryResponseDTO getSpecificDiary(@PathVariable Long diaryId){
        return diaryService.findById(diaryId);
    }

    @GetMapping(value = "/{diaryId}/delete")
    public String deleteDiary(@PathVariable Long diaryId){
        diaryService.deleteById(diaryId);
        return "deleted";
    }

    @GetMapping(value = "/music/{diaryId}")
    public List<MusicResponseDTO> getDiaryMusic(@PathVariable Long diaryId){
        return diaryService.findMusic(diaryId);
    }
}
