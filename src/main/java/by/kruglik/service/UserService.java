package by.kruglik.service;

import by.kruglik.bean.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kruglik on 14.11.2014.
 */
@Service
public class UserService {
    public List<User> getUsersForJasper(){
        User u1 = new User("1111");
        User u2 = new User("2222");
        User u3 = new User("3333");
        return Arrays.asList(u1,u2,u3);
    }
}
