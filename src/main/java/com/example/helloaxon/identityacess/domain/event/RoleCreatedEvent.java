package com.example.helloaxon.identityacess.domain.event;

import lombok.*;

import java.util.LinkedHashSet;

/**
 * @author : Ftibw
 * @date : 2021/4/20 11:56
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreatedEvent {
    private String id;
    private String name;
    private LinkedHashSet<String> functionIds;
}
