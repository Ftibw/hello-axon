package com.example.helloaxon.identityacess.infrastructure.repository;

import com.example.helloaxon.identityacess.domain.specification.IRoleSpecificationRepository;
import com.example.helloaxon.identityacess.infrastructure.query.RoleView;
import com.mongodb.client.result.DeleteResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author : Ftibw
 * @date : 2021/4/22 9:55
 */
@Slf4j
@Repository
public class RoleSpecificationRepository implements IRoleSpecificationRepository {

    private final MongoOperations mongoOperations;

    public RoleSpecificationRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    private boolean existsInView(String id, String roleName) {
        //查询任意一个带唯一约束的视图
        RoleView one = mongoOperations.findOne(
                Query.query(Criteria.where("name").is(roleName).and("id").ne(id)), RoleView.class);
        if (one == null) {
            return false;
        }
        //视图中约束已存在的话，可以清理掉视图变更时间之前的缓存
        DeleteResult result = mongoOperations.remove(
                Query.query(Criteria.where("id").lte(new ObjectId(one.getId()))),
                RoleSpecification.class
        );
        log.info(result.toString());
        return true;
    }

    @Override
    public boolean exists(String id, String roleName) {
        if (existsInView(id, roleName)) {
            return true;
        }
        try {
            mongoOperations.save(RoleSpecification.builder()
                    .id(id)
                    .name(roleName)
                    .build());
            return false;
        } catch (DuplicateKeyException ignored) {
            return true;
        }
    }

    @Override
    public void remove(String id) {
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), RoleSpecification.class);
    }

    //todo 定时刷一刷
    @Override
    public void clearExpiredCaches() {
        RoleView one = mongoOperations.findOne(new Query(), RoleView.class);
        if (one != null) {
            existsInView(one.getId(), one.getName());
        }
    }

    @Getter
    @Setter
    @Builder
    @Document
    public static class RoleSpecification {
        @Id
        private String id;
        @Indexed(unique = true)
        private String name;
    }

}
