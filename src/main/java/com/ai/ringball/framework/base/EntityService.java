package com.ai.ringball.framework.base;

public interface EntityService<T extends BaseEntity> {

	public T getEntityById(T entity);

	public T getEntityById(String entityId);
}
