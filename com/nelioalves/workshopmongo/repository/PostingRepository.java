
package com.nelioalves.workshopmongo.repository;

import com.nelioalves.workshopmongo.domain.Posting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends MongoRepository<Posting, Long> {
    
}
