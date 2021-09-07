package com.sales.management.ServiceImpl;

import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.BaseResponse;
import com.sales.management.DTO.UserDTO;
import com.sales.management.Model.User;
import com.sales.management.Repository.UserRepository;
import com.sales.management.Service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional

public class UserServiceImpl implements UserInterface {
    @Autowired
    private UserRepository userRepository;

   @Override
   public User addUserdetail(UserDTO userDTO) {
       User user = new User();
       user.setUserName(userDTO.getUserName());
       user.setPassWord(userDTO.getPassWord());
       userRepository.save(user);
       return user;
   }


    @Override
    public APIResponse<User> GetUserWithPagination(int offset, int pageSize, String userName) {
        Pageable paging= PageRequest.of(offset, pageSize);
        Page<User> Users = userRepository.searchAllByUserNameLike("%" + userName + "%", paging);
        APIResponse apiResponse=new APIResponse();
        apiResponse.setResponse(Users);
        apiResponse.setRecordCount(Users.getTotalPages());
        return apiResponse;
    }

   @Override
   public Optional<User> updateUser(UserDTO userDTO) {
       Optional<User> existUser = userRepository.findById(userDTO.getUserId());
       if(existUser.isPresent())
       {
           existUser.get().setUserName(userDTO.getUserName());
           existUser.get().setPassWord(userDTO.getPassWord());
           userRepository.save(existUser.get());
       }
       else
       {
           throw new RuntimeException("not found");
       }
       return existUser;
   }

  @Override
  public Optional<User> FindByUserId(int id){
      Optional<User> users=userRepository.findById(id);
      return users;
  }

    @Override
    public Optional<User> deletesoft(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findById(userDTO.getUserId());
        if(existUser.isPresent())
        {
            existUser.get().setIsDelete(1);
            userRepository.save(existUser.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        return existUser;
    }
}
