package com.planto.drawing.services;

import com.planto.drawing.entities.CurrentStateEntity;

public interface CurrentStateService {

    void add(CurrentStateEntity entity);

    int getCurrentIndex();

    void updateCurrentIndex(int index);

    void updateResetHistory(boolean reset);

    boolean isResetHistory();
}
