package com.chenkda.cglib;

import com.chenkeda.cglib.pojo.User;
import com.chenkeda.cglib.pojo.UserVo;
import net.sf.cglib.beans.BeanCopier;
import org.junit.After;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class BeanCopyTest {

    private BeanCopier beanCopier;

    @Test
    public void userToUserVoTest() {
        beanCopier = BeanCopier.create(User.class, UserVo.class, false);
        User user = getUser();
        UserVo userVo = new UserVo();
        beanCopier.copy(user, userVo, null);

        assertBasicUser(user, userVo);
        assertEquals(user.getPhone(), userVo.getPhone());
    }

    private void assertBasicUser(User user, UserVo userVo) {
        assertEquals(user.getId(), userVo.getId());
        assertEquals(user.getName(), userVo.getName());
        assertEquals(user.getAge(), userVo.getAge());
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("chenkeda");
        user.setAge(26);
        user.setPhone("18868195531");
        return user;
    }

    @Test
    public void userToUserVoPhoneConvertTest() {
        beanCopier = BeanCopier.create(User.class, UserVo.class, true);
        User user = getUser();
        UserVo userVo = new UserVo();
        beanCopier.copy(user, userVo, (value, target, context) -> {
            if (String.class.isAssignableFrom(target)) {
                if (Objects.equals("setPhone", context.toString())) {
                    String phone = (String) value;
                    phone = phone.substring(0,3) + "****" + phone.substring(7, 11);
                    return phone;
                }
            }
            return value;
        });

        assertBasicUser(user, userVo);
        assertEquals("188****5531", userVo.getPhone());
    }


    @After
    public void after() {
        beanCopier = null;
    }
}
