//package cn.springcloud.meisw.oauth2.service.impl;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.token.Token;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import cn.springcloud.meisw.jpa.common.MeiswException;
//import cn.springcloud.meisw.oauth2.config.OAuth2Config;
//import cn.springcloud.meisw.oauth2.dao.UmUserDao;
//import cn.springcloud.meisw.oauth2.entity.UmUser;
//import cn.springcloud.meisw.oauth2.service.UmUserService;
//
//@Service
//public class UmUserServiceImpl implements UmUserService {
//	
//	private static final Logger log = LoggerFactory.getLogger(UmUserService.class);
//	
//	@Autowired
//	private UmUserDao umUserDao;
//	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Override
//	public void saveUser(UmUser umUser) throws MeiswException {
//		long count = umUserDao.queryCountByAccount(umUser.getAccount());
//		if(count>0){
//			log.warn("用户：{}已经存在!",umUser.getAccount());
//			throw new MeiswException("用户{"+umUser.getAccount()+"}已经存在!");
//		}
//		umUserDao.saveAndFlush(umUser);
//	}
//	
//	@Override
//	public void deleteUser(Long id) throws MeiswException {
//		UmUser user = umUserDao.findById(id).get();
//		if(user == null){
//			log.warn("用户ID：{}不存在!",id);
//			throw new MeiswException("用户{"+id+"}不存在!");
//		}
//		umUserDao.deleteById(id);
//	}
//
//	@Override
//    public void updateUser(UmUser umUser) throws MeiswException {
//		UmUser user = umUserDao.findById(umUser.getId()).get();
//		if(user == null){
//			log.warn("用户不存在!");
//			throw new MeiswException("用户不存在!");
//		}
//	    umUserDao.saveAndFlush(umUser);
//    }
//
//	@Override
//    public List<UmUser> findAllUser() throws MeiswException {
//	    return umUserDao.findAll();
//    }
//
//	@Override
//    public MultiValueMap<String,Object> login(UmUser umUser) throws MeiswException {
//		MultiValueMap<String,Object> paramMap = new LinkedMultiValueMap<>();
//		paramMap.add("client_id", OAuth2Config.CLIENT_ID);
//		paramMap.add("client_secret", OAuth2Config.CLIENT_SECRET);
//		paramMap.add("username", umUser.getAccount());
//		paramMap.add("password", umUser.getPassword());
//		paramMap.add("grant_type", OAuth2Config.GRANT_TYPE[0]);
//		Token token = null;
//		try {
//            //因为oauth2本身自带的登录接口是"/oauth/token"，并且返回的数据类型不能按我们想要的去返回
//            //但是我的业务需求是，登录接口是"user/login"，由于我没研究过要怎么去修改oauth2内部的endpoint配置
//            //所以这里我用restTemplate(HTTP客户端)进行一次转发到oauth2内部的登录接口，比较简单粗暴
//            token = restTemplate.postForObject(serverConfig.getUrl() + UrlEnum.LOGIN_URL.getUrl(), paramMap, Token.class);
//            LoginUserVO loginUserVO = redisUtil.get(token.getValue(), LoginUserVO.class);
//            if(loginUserVO != null){
//                //登录的时候，判断该用户是否已经登录过了
//                //如果redis里面已经存在该用户已经登录过了的信息
//                //我这边要刷新一遍token信息，不然，它会返回上一次还未过时的token信息给你
//                //不便于做单点维护
//                token = oauthRefreshToken(loginUserVO.getRefreshToken());
//                redisUtil.deleteCache(loginUserVO.getAccessToken());
//            }
//        } catch (RestClientException e) {
//            try {
//                e.printStackTrace();
//                //此处应该用自定义异常去返回，在这里我就不去具体实现了
//                //throw new Exception("username or password error");
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//        //这里我拿到了登录成功后返回的token信息之后，我再进行一层封装，最后返回给前端的其实是LoginUserVO
//        LoginUserVO loginUserVO = new LoginUserVO();
//        User userPO = userRepository.findUserByAccount(loginUserDTO.getAccount());
//        BeanUtils.copyPropertiesIgnoreNull(userPO, loginUserVO);
//        loginUserVO.setPassword(userPO.getPassword());
//        loginUserVO.setAccessToken(token.getValue());
//        loginUserVO.setAccessTokenExpiresIn(token.getExpiresIn());
//        loginUserVO.setAccessTokenExpiration(token.getExpiration());
//        loginUserVO.setExpired(token.isExpired());
//        loginUserVO.setScope(token.getScope());
//        loginUserVO.setTokenType(token.getTokenType());
//        loginUserVO.setRefreshToken(token.getRefreshToken().getValue());
//        loginUserVO.setRefreshTokenExpiration(token.getRefreshToken().getExpiration());
//        //存储登录的用户
//        redisUtil.set(loginUserVO.getAccessToken(),loginUserVO,TimeUnit.HOURS.toSeconds(1));
//        return ResponseVO.success(loginUserVO);
//    }
//	
//}
