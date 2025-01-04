package tech.jes.smartstock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.jes.smartstock.entity.PurchaseRequestEntity;

public interface PurchaseRequestRepository  extends MongoRepository<PurchaseRequestEntity, String> {
}
