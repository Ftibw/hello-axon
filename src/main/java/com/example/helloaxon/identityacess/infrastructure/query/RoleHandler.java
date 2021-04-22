package com.example.helloaxon.identityacess.infrastructure.query;

import com.example.helloaxon.identityacess.domain.event.RoleCreatedEvent;
import com.example.helloaxon.identityacess.domain.event.RoleDeletedEvent;
import com.example.helloaxon.identityacess.domain.event.RoleUpdatedEvent;
import com.example.helloaxon.identityacess.infrastructure.repository.IRoleViewRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author : Ftibw
 * @date : 2021/4/22 15:47
 */
@Component
public class RoleHandler {

    private final IRoleViewRepository repository;

    public RoleHandler(IRoleViewRepository repository) {
        this.repository = repository;
    }

    /**
     * 事件源处理器负责视图数据异构，更新查询视图的状态
     */
    @SuppressWarnings("unused")
    @EventHandler
    private void on(RoleCreatedEvent evt) {
        repository.save(RoleView.builder()
                .id(evt.getId())
                .name(evt.getName())
                .functionIds(evt.getFunctionIds())
                .build());
    }

    @SuppressWarnings("unused")
    @EventHandler
    private void on(RoleUpdatedEvent evt) {
        repository.save(RoleView.builder()
                .id(evt.getId())
                .name(evt.getName())
                .functionIds(evt.getFunctionIds())
                .build());
    }

    @SuppressWarnings("unused")
    @EventHandler
    private void on(RoleDeletedEvent evt) {
        repository.deleteById(evt.getId());
    }

}
