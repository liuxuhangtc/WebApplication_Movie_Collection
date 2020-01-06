package me.xuhang.movie.utils;

import lombok.Data;
import me.xuhang.movie.entity.User;

/**
 * Created by xuhang on 2019/11/20.
 */
@Data
public class SessionUtils {

    /**
     * The current login user.
     */
    private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


    
}
