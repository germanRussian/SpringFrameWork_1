package com.kb.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select(" select now() ")
	public String getTime();
	
	
	
	public String getTime2();
	//getTime2()가 xml파일에서 id가 된다.
}
