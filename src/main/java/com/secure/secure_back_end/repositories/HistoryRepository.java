package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>
{
}
