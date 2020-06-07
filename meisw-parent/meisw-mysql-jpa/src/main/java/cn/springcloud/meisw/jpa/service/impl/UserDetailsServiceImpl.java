//package cn.springcloud.meisw.jpa.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import cn.springcloud.meisw.jpa.conf.security.dto.SecurityUser;
//import cn.springcloud.meisw.jpa.dao.SysRoleDao;
//import cn.springcloud.meisw.jpa.dao.SysUserDao;
//import cn.springcloud.meisw.jpa.dao.SysUserRoleDao;
//import cn.springcloud.meisw.jpa.po.SysRole;
//import cn.springcloud.meisw.jpa.po.SysUser;
//import cn.springcloud.meisw.jpa.po.SysUserRole;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * <p> 自定义userDetailsService - 认证用户详情 </p>
// *
// * @author : zhengqing
// * @description :
// * @date : 2019/10/14 17:46
// */
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
////    @Autowired
////    private UserMapper userMapper;
////    @Autowired
////    private RoleMapper roleMapper;
////    @Autowired
////    private UserRoleMapper userRoleMapper;
//	
//	@Autowired
//	private SysUserDao sysUserDao;
//	
//	@Autowired
//	private SysRoleDao sysRoleDao;
//	
//	@Autowired
//	private SysUserRoleDao sysUserRoleDao;
//
//    /***
//     * 根据账号获取用户信息
//     * @param username:
//     * @return: org.springframework.security.core.userdetails.UserDetails
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 从数据库中取出用户信息
////        List<User> userList = userMapper.selectList(new EntityWrapper<User>().eq("username", username));
////        User user;
//    	SysUser sysUser;
//    	List<SysUser> sysUserList = sysUserDao.findByUsername(username);
//        // 判断用户是否存在
//        if (!CollectionUtils.isEmpty(sysUserList)) {
////            user = userList.get(0);
//        	sysUser = sysUserList.get(0);
//        } else {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
//        // 返回UserDetails实现类
//        return new SecurityUser(sysUser, getUserRoles(sysUser.getId()));
//    }
//
//    /***
//     * 根据token获取用户权限与基本信息
//     *
//     * @param token:
//     * @return: com.zhengqing.config.security.dto.SecurityUser
//     */
//    public SecurityUser getUserByToken(String token) {
//        SysUser user = null;
//        List<SysUser> loginList = sysUserDao.findByToken(token);
//        if (!CollectionUtils.isEmpty(loginList)) {
//            user = loginList.get(0);
//        }
//        return user != null ? new SecurityUser(user, getUserRoles(user.getId())) : null;
//    }
//
//    /**
//     * 根据用户id获取角色权限信息
//     *
//     * @param userId
//     * @return
//     */
//    private List<SysRole> getUserRoles(Long userId) {
////        List<UserRole> userRoles = userRoleMapper.selectList(new EntityWrapper<UserRole>().eq("user_id", userId));
//    	List<SysUserRole> userRoles = sysUserRoleDao.queryByUserId(userId);
//        List<SysRole> roleList = new LinkedList<>();
//        for (SysUserRole userRole : userRoles) {
////            SysRole role = roleMapper.selectById(userRole.getRoleId());
//        	SysRole role = sysRoleDao.findById(userRole.getRoleId()).get();
//            roleList.add(role);
//        }
//        return roleList;
//    }
//
//}
