package com.example.helloaxon.identityacess.domain.event;

import lombok.*;

import java.util.LinkedHashSet;

/**
 * @author : Ftibw
 * @date : 2021/4/20 11:57
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdatedEvent {
    private String id;
    private String name;
    private LinkedHashSet<String> functionIds;
}
