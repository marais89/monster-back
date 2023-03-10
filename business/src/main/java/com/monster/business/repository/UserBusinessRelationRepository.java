package com.monster.business.repository;

import java.util.List;

import com.monster.business.entity.UserBusinessRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBusinessRelationRepository extends CrudRepository<UserBusinessRelation, Integer> {

    List<UserBusinessRelation> findUserBusinessRelationByIndividuId(int individuId);

    List<UserBusinessRelation> findUserBusinessRelationByBusinessId(int businessId);

    List<UserBusinessRelation> findUserBusinessRelationByGroupId(int groupId);
}
