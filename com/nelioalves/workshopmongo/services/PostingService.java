package com.nelioalves.workshopmongo.services;

import com.nelioalves.workshopmongo.domain.Posting;
import com.nelioalves.workshopmongo.repository.PostingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingService {

    private final PostingRepository postingRepository;

    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    public Posting savePosting(Posting posting) {
        return postingRepository.save(posting);
    }

    public List<Posting> getAllPostings() {
        return postingRepository.findAll();
    }

	public Posting getPostingById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}