package com.hccake.ballcat.starter.ip2region.searcher;

import com.hccake.ballcat.starter.ip2region.core.IpInfo;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试
 *
 * @author lishangbu
 * @date 2022/10/16
 */
@SpringBootApplication(scanBasePackages = "com.hccake.ballcat.starter.ip2region")
@SpringBootTest
public class Ip2regionSearcherTestTemplate {

	@Autowired
	protected Ip2regionSearcher ip2regionSearcher;

	protected void testIpSearch() {
		IpInfo localIp = ip2regionSearcher.search("127.0.0.1");
		Assertions.assertNull(localIp.getCountry());
		Assertions.assertNull(localIp.getProvince());
		Assertions.assertNull(localIp.getArea());
		Assertions.assertEquals("内网IP", localIp.getCity());
		Assertions.assertEquals("内网IP", localIp.getIsp());
		Assertions.assertEquals("127.0.0.1", localIp.getOriginIp());
		Assertions.assertEquals("内网IP", localIp.getAddress());
		Assertions.assertEquals("内网IP", localIp.getAddress(","));
		Assertions.assertEquals("内网IP", localIp.getAddressAndIsp());
		Assertions.assertEquals("内网IP", localIp.getAddressAndIsp(","));

		IpInfo remoteIp = ip2regionSearcher.search("39.188.108.178");
		Assertions.assertEquals("中国", remoteIp.getCountry());
		Assertions.assertEquals("浙江省", remoteIp.getProvince());
		Assertions.assertNull(localIp.getArea());
		Assertions.assertEquals("宁波市", remoteIp.getCity());
		Assertions.assertEquals("移动", remoteIp.getIsp());
		Assertions.assertEquals("39.188.108.178", remoteIp.getOriginIp());
		Assertions.assertEquals("中国浙江省宁波市", remoteIp.getAddress());
		Assertions.assertEquals("中国 浙江省 宁波市", remoteIp.getAddress(" "));
		Assertions.assertEquals("中国浙江省宁波市移动", remoteIp.getAddressAndIsp());
		Assertions.assertEquals("中国 浙江省 宁波市 移动", remoteIp.getAddressAndIsp(" "));
	}

}
