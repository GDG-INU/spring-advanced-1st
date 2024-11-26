package com.gdg.ms.service;

import com.gdg.ms.entity.Manager;
import com.gdg.ms.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    // 매니저 회원 여부 확인
    public boolean validateManager(Manager manager) {
        String managerId = manager.getId();
        String managerPassword = manager.getPassword();

        Optional<Manager> managerOpt = managerRepository.findById(managerId);

        if (managerOpt.isPresent() && managerOpt.get().getPassword().equals(managerPassword)) {
            return true;
        }else {
            return false;
        }

    }

    // 매니저 회원 정보
    public Manager getManagerInfo(Manager manager) {

        Optional<Manager> managerOpt = managerRepository.findById(manager.getId());
        return managerOpt.orElse(null);
    }


}
