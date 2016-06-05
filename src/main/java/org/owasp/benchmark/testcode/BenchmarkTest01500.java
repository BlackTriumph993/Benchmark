/**
* OWASP Benchmark Project v1.2
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Dave Wichers <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/pathtraver-01/BenchmarkTest01500")
public class BenchmarkTest01500 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		org.owasp.benchmark.helpers.SeparateClassRequest scr = new org.owasp.benchmark.helpers.SeparateClassRequest( request );
		String param = scr.getTheParameter("BenchmarkTest01500");
		if (param == null) param = "";

		String bar = new Test().doSomething(param);
		
		String fileName = org.owasp.benchmark.helpers.Utils.testfileDir + bar;
        java.io.InputStream is = null;
        
		try {	
			java.nio.file.Path path = java.nio.file.Paths.get(fileName);
			is = java.nio.file.Files.newInputStream(path, java.nio.file.StandardOpenOption.READ);
			byte[] b = new byte[1000];
			int size = is.read(b);
			response.getWriter().println(
"The beginning of file: '" + org.owasp.esapi.ESAPI.encoder().encodeForHTML(fileName) + "' is:\n\n"
);
			response.getWriter().println(
org.owasp.esapi.ESAPI.encoder().encodeForHTML(new String(b,0,size))
);
			is.close();
		} catch (Exception e) {
            System.out.println("Couldn't open InputStream on file: '" + fileName + "'");
			response.getWriter().println(
"Problem getting InputStream: " 
				+ org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage())
);
        } finally {
			if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (Exception e) {
                    // we tried...
                }
            }
        }
	}  // end doPost

	
    private class Test {

        public String doSomething(String param) throws ServletException, IOException {

		String bar = "safe!";
		java.util.HashMap<String,Object> map3545 = new java.util.HashMap<String,Object>();
		map3545.put("keyA-3545", "a Value"); // put some stuff in the collection
		map3545.put("keyB-3545", param); // put it in a collection
		map3545.put("keyC", "another Value"); // put some stuff in the collection
		bar = (String)map3545.get("keyB-3545"); // get it back out

            return bar;
        }
    } // end innerclass Test

} // end DataflowThruInnerClass
