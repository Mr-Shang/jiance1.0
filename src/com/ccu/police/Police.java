package com.ccu.police;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.hibernate.Query;
import org.hibernate.Session;
import com.ccu.util.hibernate.HibernateUtils;

/**
 * 报警事件数头部显示
 * @author fly
 *
 */
public class Police implements ServletContextListener{
	private static final String CHANNEL = "police";
    public void contextInitialized(ServletContextEvent arg0) {
            CometContext cc = CometContext.getInstance();
            cc.registChannel(CHANNEL);
            Thread policeAppModule = new Thread(new PoliceAppModule(), "Sender App Module");
            policeAppModule.setDaemon(true);
            //policeAppModule.start();
    }

    class PoliceAppModule implements Runnable {
            public void run() {
                    while (true) {
                            try {
                                Thread.sleep(5000);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            CometEngine engine = CometContext.getInstance().getEngine();
                            engine.sendToAll(CHANNEL, getName());
                    }
            }
    }

  //调用数据库连接操作
    

    public String getName(){
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        String hql = "select count(*) from EventInfo where IsHandle = 'false'";
        Query query = session.createQuery(hql);
        Long evSize = (Long)query.uniqueResult();
   		if(evSize>0){
			return String.valueOf(evSize);
		}else{
			return "0";
		}
    }
   
    public void contextDestroyed(ServletContextEvent arg0) {

    }
}
