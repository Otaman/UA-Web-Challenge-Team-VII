package ua.web_challenge.volunteer.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * The filter that consider HEAD requests as GET
 * @author Bohdan Vanchuhov
 */
public class HttpHeadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (isHttpHead(httpServletRequest)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            NoBodyResponseWrapper noBodyResponseWrapper = new NoBodyResponseWrapper(httpServletResponse);

            chain.doFilter(new ForceGetRequestWrapper(httpServletRequest), noBodyResponseWrapper);
            noBodyResponseWrapper.setContentLength();
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isHttpHead(HttpServletRequest request) {
        return "HEAD".equals(request.getMethod());
    }

    private class ForceGetRequestWrapper extends HttpServletRequestWrapper {
        public ForceGetRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getMethod() {
            return "GET";
        }
    }

    private class NoBodyResponseWrapper extends HttpServletResponseWrapper {
        private final NoBodyOutputStream noBodyOutputStream = new NoBodyOutputStream();
        private PrintWriter writer;

        public NoBodyResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return noBodyOutputStream;
        }

        @Override
        public PrintWriter getWriter() throws UnsupportedEncodingException {
            if (writer == null) {
                writer = new PrintWriter(new OutputStreamWriter(noBodyOutputStream, getCharacterEncoding()));
            }

            return writer;
        }

        void setContentLength() {
            super.setContentLength(noBodyOutputStream.getContentLength());
        }
    }

    private class NoBodyOutputStream extends ServletOutputStream {
        private int contentLength = 0;

        int getContentLength() {
            return contentLength;
        }

        @Override
        public void write(int b) {
            contentLength++;
        }

        @Override
        public void write(byte buf[], int offset, int len) throws IOException {
            contentLength += len;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
        }
    }
}
