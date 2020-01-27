package com.pw.mini.mogger.user.boundary;

import com.pw.mini.mogger.user.boundary.command.CreateNewUserCommand;
import com.pw.mini.mogger.user.boundary.vo.UserVO;
import com.pw.mini.mogger.user.control.mapper.UserMapper;
import com.pw.mini.mogger.user.control.repository.UserRepository;
import com.pw.mini.mogger.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateNewUserCommand command) {
        UserEntity userEntity = UserEntity.builder()
                .userName(command.getUserName())
                .password(command.getPassword())
                .build();
        entityManager.persist(userEntity);
    }

    public boolean checkUser(String userName, String password) {
        try {
            UserEntity userEntity = userRepository.findByUserName(userName);
            return userEntity.getPassword().equals(password);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<UserVO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(e-> UserMapper.INSTANCE.mapEntity(e))
                .collect(Collectors.toList());
    }
}
