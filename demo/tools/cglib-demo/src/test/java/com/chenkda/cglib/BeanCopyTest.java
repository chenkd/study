package com.chenkda.cglib;

import com.chenkeda.cglib.pojo.User;
import com.chenkeda.cglib.pojo.UserVo;
import net.sf.cglib.beans.BeanCopier;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BeanCopyTest {

    private static BeanCopier beanCopier;

    @BeforeClass
    public static void beforeClass() {
        beanCopier = BeanCopier.create(User.class, UserVo.class, true);
    }

    @Test
    public void userToUserVoTest() {
        User user = getUser();
        UserVo userVo = new UserVo();
        beanCopier.copy(user, userVo, null);

        assertBasicUser(user, userVo);
        assertNull(userVo.getPhone());
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
}
