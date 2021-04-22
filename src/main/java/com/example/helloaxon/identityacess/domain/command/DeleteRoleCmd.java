package com.example.helloaxon.identityacess.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author : Ftibw
 * @date : 2021/4/20 12:01
 */
@Getter
@AllArgsConstructor
public class DeleteRoleCmd {
    @TargetAggregateIdentifier
    private final String id;
}
