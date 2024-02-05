package org.changsol.api.configs.filters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ChangSolHttpServletRequest extends HttpServletRequestWrapper {
	private ByteArrayOutputStream cachedBytes;

	public ChangSolHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (cachedBytes == null)
			cacheInputStream();

		return new CachedServletInputStream(cachedBytes.toByteArray());
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	private void cacheInputStream() throws IOException {
		/* Cache the inputstream in order to read it multiple times. For
		 * convenience, I use apache.commons IOUtils
		 */
		cachedBytes = new ByteArrayOutputStream();
		IOUtils.copy(super.getInputStream(), cachedBytes);
	}


	/* An input stream which reads the cached request body */
	private static class CachedServletInputStream extends ServletInputStream {

		private final ByteArrayInputStream buffer;

		public CachedServletInputStream(byte[] contents) {
			this.buffer = new ByteArrayInputStream(contents);
		}

		@Override
		public int read() {
			return buffer.read();
		}

		@Override
		public boolean isFinished() {
			return buffer.available() == 0;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			throw new RuntimeException("Not implemented");
		}
	}

	@Override
	public String getRemoteAddr() {
		String ip = getClientIP(this);

		if (StringUtils.isEmpty(ip)) {
			ip = super.getRemoteAddr();
		}

		return ip;
	}

	@Override
	public String getRemoteHost() {
		try {
			return InetAddress.getByName(getRemoteAddr()).getHostName();
		} catch (UnknownHostException e) {
			return getRemoteAddr();
		}
	}

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");

		if ("127.0.0.1".equals(ip) || "::1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = null;
		}

		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader("X-Forwarded-For");

			if (StringUtils.contains(ip, ",")) {
				ip = StringUtils.split(ip, ",")[0];
			}

			if ("127.0.0.1".equals(ip) || "::1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = null;
			}
		}

		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if ("127.0.0.1".equals(ip) || "::1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = null;
			}
		}

		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
			if ("127.0.0.1".equals(ip) || "::1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = null;
			}
		}
		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			if ("127.0.0.1".equals(ip) || "::1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = null;
			}
		}
		if (StringUtils.isEmpty(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			if ("127.0.0.1".equals(ip) || "::1".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = null;
			}
		}
		return ip;
	}

}
