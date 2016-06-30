import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class GradeBookServer {

	//TODO: change the default port to your assigned port
	//Consider making this a command line argument instead!
	public static final int DEFAULT_PORT = 3001;
	
	
	public static void main(String[] args) throws Exception { //<- not recommended to throw Exception in general, but hard to avoid in this case
		Server server = new Server(DEFAULT_PORT);

		//create a ServletHander to attach servlets
		ServletContextHandler servhandler = new ServletContextHandler(ServletContextHandler.SESSIONS);        
		server.setHandler(servhandler);

		servhandler.addEventListener(new ServletContextListener() {

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				//Do nothing when server shut down.
			}

			@Override
			public void contextInitialized(ServletContextEvent sce) {

				Path path = Paths.get("grades.json");
				GradeBook book = GradeBookBuilder.buildBook(path);
				//if grades file is not valid then create an empty GradeBook.
				if(book == null) {
					book = new GradeBook();
				}
				sce.getServletContext().setAttribute("gradebook", book);
			}

		});

		//add a servlet for searching for grades
		servhandler.addServlet(GradeSearchServlet.class, "/search");

		//TODO: add a servlet for displaying grades of all students in the GradeBook
		servhandler.addServlet(GradeAllServlet.class, "/all");
		
		//set the list of handlers for the server
		server.setHandler(servhandler);

		server.start();
		server.join();
	}

}
