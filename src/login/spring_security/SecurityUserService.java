package login.spring_security;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Created by Kevin on 2016/10/27.
 */
public class SecurityUserService {
//        implements UserDetailsService {
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }

//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("username is " + username);
//        AcSysUser user = cwSysUserDAO.findUser(username);
//        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
//        return buildUserForAuthentication(user, authorities);
//    }
//
//    /**
//     * 返回验证角色
//     */
//    private List<GrantedAuthority> buildUserAuthority(Set<CwSysUserRole> userRoles) {
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//        for (CwSysUserRole userRole : userRoles) {
//            setAuths.add(new SimpleGrantedAuthority(userRole.getRole().getRoleId().toString()));
//        }
//        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
//        return result;
//    }
//
//    /**
//     * 返回验证用户
//     */
//    private User buildUserForAuthentication(AcSysUser user, List<GrantedAuthority> authorities) {
//        return new User(user.getUserNo(), user.getPassword(), true, true, true, true, authorities);
//    }
}
