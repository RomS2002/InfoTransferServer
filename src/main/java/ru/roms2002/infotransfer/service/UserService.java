package ru.roms2002.infotransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.roms2002.infotransfer.entity.UserEntity;
import ru.roms2002.infotransfer.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserEntity> findByRegToken(String regToken) {
		return userRepository.findByRegToken(regToken);
	}
}
