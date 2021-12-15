package com.inetum.academy.mongo.tolkien;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmyRepository extends MongoRepository<Army, String>  {

    @Query("{ soldiersCount: { $gte: ?0 },  power: { $gt: ?1 } }")
    List<Army> findArmiesWithEnoughPower(int minSoldiersCount, double minPower);

}
