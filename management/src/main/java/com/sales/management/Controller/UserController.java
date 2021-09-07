package com.sales.management.Controller;


import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.baseresp;
import com.sales.management.DTO.UserDTO;
import com.sales.management.Model.User;
import com.sales.management.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceimpl;

    @PostMapping("/add")
    public baseresp<User> adduserdetail(@Valid @RequestBody UserDTO userDTO) {
        baseresp<User> base=null;
        base=baseresp.<User>builder().Data(userServiceimpl.addUserdetail(userDTO)).build();
        return base ;
    }

     @GetMapping("/{offset}/{pageSize}/{userName}")
     private APIResponse<User> getUserWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String userName) {
     return userServiceimpl.GetUserWithPagination(offset, pageSize, userName);
    }

    @PutMapping("/updater")
    public baseresp<Optional<User>> updateuser(@Valid @RequestBody UserDTO userDTO) {
        baseresp<Optional<User>> base=null;
        base=baseresp.<Optional<User>>builder().Data(userServiceimpl.updateUser(userDTO)).build();
        return base ;
    }

    @GetMapping("/{id}")
    public baseresp<Optional<User>> FindByUserId(@PathVariable int id){
        baseresp<Optional<User>> base=null;
        base =baseresp.<Optional<User>>builder().Data(userServiceimpl.FindByUserId(id)).build();
        return base;
    }

    @PutMapping("/delete")
     public baseresp<Optional<User>> deletesoft(@RequestBody UserDTO userDTO) {
        baseresp<Optional<User>> base=null;
        base=baseresp.<Optional<User>>builder().Data(userServiceimpl.deletesoft(userDTO)).build();
        return base ;
    }




}
