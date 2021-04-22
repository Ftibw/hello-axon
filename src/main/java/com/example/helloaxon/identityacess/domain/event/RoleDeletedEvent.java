package com.example.helloaxon.identityacess.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Ftibw
 * @date : 2021/4/20 11:57
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDeletedEvent {
   private String id;
}
