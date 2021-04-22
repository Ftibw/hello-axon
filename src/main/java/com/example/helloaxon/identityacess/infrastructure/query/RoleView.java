package com.example.helloaxon.identityacess.infrastructure.query;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;

/**
 * @author : Ftibw
 * @date : 2021/4/22 10:06
 */
@Getter
@Setter
@Builder
@Document
public class RoleView {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private LinkedHashSet<String> functionIds;
}
