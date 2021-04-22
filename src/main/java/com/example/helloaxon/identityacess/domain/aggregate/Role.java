package com.example.helloaxon.identityacess.domain.aggregate;

import com.example.helloaxon.identityacess.domain.command.CreateRoleCmd;
import com.example.helloaxon.identityacess.domain.command.DeleteRoleCmd;
import com.example.helloaxon.identityacess.domain.command.UpdateRoleCmd;
import com.example.helloaxon.identityacess.domain.event.RoleCreatedEvent;
import com.example.helloaxon.identityacess.domain.event.RoleDeletedEvent;
import com.example.helloaxon.identityacess.domain.event.RoleUpdatedEvent;
import com.example.helloaxon.identityacess.domain.specification.IRoleSpecificationRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.bson.types.ObjectId;

import java.util.LinkedHashSet;

/**
 * @author : Ftibw
 * @date : 2021/4/7 14:49
 */
@Aggregate
public class Role {
    @AggregateIdentifier
    private String id;
    private String name;
    private LinkedHashSet<String> functionIds;

    protected Role() {
    }

    /**
     * 命令处理器负责业务逻辑校验，领域事件分派，而不进行聚合状态变更
     */
    @CommandHandler
    public Role(CreateRoleCmd cmd, IRoleSpecificationRepository repository) {
        String id = new ObjectId().toHexString();
        String name = cmd.getName();
        if (repository.exists(id, name)) {
            throw new IllegalArgumentException("角色名称已存在");
        }
        AggregateLifecycle.apply(new RoleCreatedEvent(id, name, cmd.getFunctionIds()));
    }

    @SuppressWarnings("unused")
    @CommandHandler
    public void handle(UpdateRoleCmd cmd, IRoleSpecificationRepository repository) {
        String id = cmd.getId();
        String newName = cmd.getName();
        if (repository.exists(id, newName)) {
            throw new IllegalArgumentException("角色名称已存在");
        }
        AggregateLifecycle.apply(new RoleUpdatedEvent());
    }

    @SuppressWarnings("unused")
    @CommandHandler
    public void handle(DeleteRoleCmd cmd, IRoleSpecificationRepository repository) {
        String id = cmd.getId();
        repository.remove(id);
        AggregateLifecycle.apply(new RoleDeletedEvent(id));
    }

    /**
     * 事件源处理器负责处理业务逻辑，进行聚合状态变更
     */
    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(RoleCreatedEvent evt) {
        this.id = evt.getId();
        this.name = evt.getName();
        this.functionIds = evt.getFunctionIds();
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(RoleUpdatedEvent evt) {
        this.name = evt.getName();
        this.functionIds = evt.getFunctionIds();
    }

    @SuppressWarnings("unused")
    @EventSourcingHandler
    private void on(RoleDeletedEvent evt) {
        AggregateLifecycle.markDeleted();
    }

}
