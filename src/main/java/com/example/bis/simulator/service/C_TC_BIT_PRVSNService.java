package com.example.bis.simulator.service;

import com.example.bis.simulator.model.C_TC_BIT_PRVSN;
import com.example.bis.simulator.repository.C_TC_BIT_PRVSNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class C_TC_BIT_PRVSNService {

    private final C_TC_BIT_PRVSNRepository bitPrvsnRepository;

    @Autowired
    public C_TC_BIT_PRVSNService(C_TC_BIT_PRVSNRepository bitPrvsnRepository) {
        this.bitPrvsnRepository = bitPrvsnRepository;
    }

    public void createMessage(C_TC_BIT_PRVSN message) {
        bitPrvsnRepository.save(message);
    }

    public C_TC_BIT_PRVSN getMessageById(String bitId) {
        return bitPrvsnRepository.findById(bitId).orElse(null);
    }

    public boolean deleteMessageById(String bitId) {
        if (bitPrvsnRepository.existsById(bitId)) {
            bitPrvsnRepository.deleteById(bitId);
            return true;
        }
        return false;
    }
}