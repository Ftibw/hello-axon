package com.example.helloaxon.identityacess.domain.specification;

/**
 * 先查视图检查唯一约束，若视图满足唯一约束，
 * 再查缓存检查唯一约束 （例如缓存最新的N次同类事件，N次事件发生时间作为窗口期，期望视图在窗口期内完成了更新）
 * 若缓存满足唯一约束，则进行聚合的后续操作
 * 最终视图更新时，若出现唯一约束异常，需要进行补偿（抛出一个删除聚合事件）
 * <p>
 * 如果缓存是全量的，且添加时总是先查视图，删除时总是先删缓存
 * 只要保证缓存添加/修改的一致性的就能完全保证约束
 *
 * @author : Ftibw
 * @date : 2021/4/22 9:31
 */
public interface IRoleSpecificationRepository {

    /**
     * 1.先查视图表是否存在
     * 2.视图表不存在，直接插入存在约束的缓存表，无异常则继续聚合操作
     */
    boolean existsOnCreate(String id, String roleName);

    /**
     * 1.先查视图表是否存在
     * 2.视图表不存在，直接修改存在约束的缓存表，无异常则继续聚合操作
     */
    boolean existsOnUpdate(String id, String roleName);

    /**
     * 删除缓存
     */
    void remove(String id);

}
