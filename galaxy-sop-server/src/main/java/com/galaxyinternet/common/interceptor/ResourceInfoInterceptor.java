package com.galaxyinternet.common.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.galaxyinternet.common.annotation.GalaxyResource;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.user.User;
/**
 * 数据权限控制, 通过在controller方法上添加{@link GalaxyResource}注解控制数据范围
 * 
 * @author wangsong
 *
 */
public class ResourceInfoInterceptor extends HandlerInterceptorAdapter 
{
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if(HandlerMethod.class.isInstance(handler))
		{
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			Method method = handlerMethod.getMethod();
			GalaxyResource rec = method.getAnnotation(GalaxyResource.class);
			if(rec != null)
			{
				HttpSession session = request.getSession();
				String pageId = rec.name();
				List<String> tables = Arrays.asList(rec.table());
				AuthContext.get().setTables(tables);
				User user = (User)session.getAttribute(Constants.SESSION_USER_KEY);
				if(user != null)
				{
					List<PlatformResource> list = user.getAllResourceToUser();
					if(list != null)
					{
						for(PlatformResource item : list)
						{
							if(pageId.equals(item.getResourceMark()) && item.getUserIds() != null && item.getUserIds().size() >0)
							{
								AuthContext.get().setUserIds(item.getUserIds());
							}
						}
					}
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		AuthContext.remove();
		super.afterCompletion(request, response, handler, ex);
	}

	
}
