package com.zahra.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zahra.app.model.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {

}
