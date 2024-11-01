package com.example.bis.simulator.controller;

import com.example.bis.simulator.model.C_TC_BIT_PRVSN;
import com.example.bis.simulator.service.C_TC_BIT_PRVSNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/bus/messages")
public class C_TC_BIT_PRVSNController {

    private final C_TC_BIT_PRVSNService bitPrvsnService;

    @Autowired
    public C_TC_BIT_PRVSNController(C_TC_BIT_PRVSNService bitPrvsnService) {
        this.bitPrvsnService = bitPrvsnService;
    }

    // 메시지 생성
    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody C_TC_BIT_PRVSN message) {
        message.setPrvsnDt(new Date()); // 메시지 생성 시 날짜 설정
        bitPrvsnService.createMessage(message);
        return ResponseEntity.ok("Message created with ID: " + message.getBitId());
    }

    //조회
    @GetMapping("/{bitId}")
    public ResponseEntity<C_TC_BIT_PRVSN> getMessage(@PathVariable String bitId) {
        C_TC_BIT_PRVSN message = bitPrvsnService.getMessageById(bitId);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    // 메시지 삭제
//    @DeleteMapping("/{bitId}")
//    public ResponseEntity<String> deleteMessage(@PathVariable String bitId) {
//        boolean deleted = bitPrvsnService.deleteMessageById(bitId);
//        if (deleted) {
//            return ResponseEntity.ok("Message deleted with ID: " + bitId);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}