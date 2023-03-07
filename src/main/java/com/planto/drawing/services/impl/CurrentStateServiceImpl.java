package com.planto.drawing.services.impl;

import com.planto.drawing.entities.CurrentStateEntity;
import com.planto.drawing.repositories.CurrentStateRepository;
import com.planto.drawing.services.CurrentStateService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentStateServiceImpl implements CurrentStateService {

    private final CurrentStateRepository currentStateRepository;

    @Autowired
    public CurrentStateServiceImpl(CurrentStateRepository currentStateRepository) {
        this.currentStateRepository = currentStateRepository;
    }

    @Override
    public void add(@NonNull CurrentStateEntity entity) {
        currentStateRepository.save(entity);
    }

    @Override
    public int getCurrentIndex() {
        return currentStateRepository.getCurrentIndex();
    }

    @Override
    public void updateCurrentIndex(int index) {
        currentStateRepository.updateCurrentIndex(index);
    }

    @Override
    public void updateResetHistory(boolean reset) {
        currentStateRepository.updateResetHistory(reset);
    }

    @Override
    public boolean isResetHistory() {
        return currentStateRepository.isResetHistory();
    }
}
