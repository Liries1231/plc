package com.example.CodeLC.repos;

import com.example.CodeLC.domain.Message;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepos extends PagingAndSortingRepository<Message, Long> {


}
