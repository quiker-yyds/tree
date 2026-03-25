package org.zx.tree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zx.tree.entity.Reflection;
import java.util.List;

@Repository
public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
    List<Reflection> findByPublishedTrueOrderByCreateTimeDesc();
    List<Reflection> findAllByOrderByCreateTimeDesc();
}