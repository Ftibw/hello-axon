package com.example.helloaxon.identityacess.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.LinkedHashSet;

/**
 * @author : Ftibw
 * @date : 2021/4/20 11:14
 */
@Getter
@Setter
@AllArgsConstructor
public class CreateRoleCmd {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private LinkedHashSet<String> functionIds;
}
