package stackjava.com.democdi.interceptors;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class MainApp {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		BeanManager bm = container.getBeanManager();

		Bean<Hello> bean = (Bean<Hello>) bm.getBeans("hello").iterator().next();
		CreationalContext<Hello> ctx = bm.createCreationalContext(bean);
		Hello hello = (Hello) bm.getReference(bean, Hello.class, ctx);
		hello.printHello();
		System.out.println("----------------");
		hello.printOk();

	}

}
