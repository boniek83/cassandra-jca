/*
 *  Copyright (C) 2013 sergey.sarabun@gmail.com.
 * 
 *  This library is free software: you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public 
 *  License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ssarabun.jca.cassandra.example;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.cassandra.thrift.KsDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.cassandra.jca.api.CassandraConnection;
import com.googlecode.cassandra.jca.api.CassandraConnectionFactory;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jun 23, 2013
 */
@Singleton
@Startup
@Local(MySingleton.class)
public class MySingletonBean implements MySingleton {

    private static final Logger logger = LoggerFactory.getLogger(MySingletonBean.class);
    @Resource(mappedName = "java:/eis/CassandraConnectionFactory")
    private CassandraConnectionFactory cf;

    @PostConstruct
    public void postConstruct() throws Exception {
        CassandraConnection c = null;
        try {
            c = cf.getConnection();

            List<KsDef> list = c.getInternalConnection().describe_keyspaces();
            for (KsDef ksDef : list) {
                logger.info("keyspace_name = " + ksDef.name);
            }
            
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
}
