package com.example.helloaxon.identityacess.infrastructure.repository;

import com.example.helloaxon.identityacess.infrastructure.query.RoleView;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 先查视图检查唯一约束，如果要求不高，验证到这里即可。
 * 若需要更强的一致性， 则当视图满足唯一约束，再查缓存检查唯一约束（例如缓存最新的N次同类事件，N次事件发生时间作为窗口期，期望视图在窗口期内完成了更新）
 * 若缓存满足唯一约束，则进行聚合的后续操作
 * 最终视图更新时，若出现唯一约束异常，需要进行补偿（抛出一个删除聚合事件）
 *
 * @author : Ftibw
 * @date : 2021/4/22 9:31
 */
public interface IRoleViewRepository extends MongoRepository<RoleView,String> {

}
