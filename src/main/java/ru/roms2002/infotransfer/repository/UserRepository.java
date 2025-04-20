package ru.roms2002.infotransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.roms2002.infotransfer.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByRegToken(String regToken);
}
