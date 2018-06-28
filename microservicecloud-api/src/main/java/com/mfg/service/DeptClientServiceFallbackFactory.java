package com.mfg.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mfg.entity.Dept;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>
{
	@Override
	public DeptClientService create(Throwable throwable)
	{
		return new DeptClientService() {
			@Override
			public Dept get(long id)
			{
				Dept dept= new Dept();
				dept.setDeptno(id);
				dept.setDname("没有该部门信息，这里是熔断器");
				return dept;
			}

			@Override
			public List<Dept> list()
			{
				return null;
			}

			@Override
			public boolean add(Dept dept)
			{
				return false;
			}
		};
	}
}
