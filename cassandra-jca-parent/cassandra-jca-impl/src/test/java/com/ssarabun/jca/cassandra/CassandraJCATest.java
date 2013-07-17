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
package com.ssarabun.jca.cassandra;

import java.util.UUID;

import javax.annotation.Resource;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.ResourceAdapterArchive;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ssarabun.jca.cassandra.api.CassandraConnection;
import com.ssarabun.jca.cassandra.api.CassandraConnectionFactory;
import com.ssarabun.jca.cassandra.connection.ClosedCassandraIface;
import com.ssarabun.jca.cassandra.connection.exception.ClosedCassandraIfaceException;
import com.ssarabun.jca.cassandra.connection.factory.CassandraManagedConnectionFactory;
import com.ssarabun.jca.cassandra.managed.connection.CassandraManagedConnection;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jul 17, 2013
 */
@RunWith(Arquillian.class)
public class CassandraJCATest {

    private static String deploymentName = "ConnectorTestCase";

    @Deployment
    public static ResourceAdapterArchive createDeployment() {
        ResourceAdapterArchive raa =
                ShrinkWrap.create(ResourceAdapterArchive.class, deploymentName + ".rar");
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class,
                UUID.randomUUID().toString() + ".jar");
        jar.addPackage(CassandraResourceAdapter.class.getPackage());
        jar.addPackage(ClosedCassandraIface.class.getPackage());
        jar.addPackage(CassandraConnection.class.getPackage());
        jar.addPackage(ClosedCassandraIfaceException.class.getPackage());
        jar.addPackage(CassandraManagedConnectionFactory.class.getPackage());
        jar.addPackage(CassandraManagedConnection.class.getPackage());
        raa.addAsLibrary(jar);
        raa.addAsManifestResource("META-INF/ironjacamar.xml", "ironjacamar.xml");
        raa.addAsManifestResource("META-INF/ra.xml", "ra.xml");

        return raa;
    }
    /** resource */
    @Resource(mappedName = "java:/eis/CassandraConnectionFactory")
    private CassandraConnectionFactory cf;

    @Test
    public void testHelloWorldNoArgs() throws Throwable {
        assertNotNull(cf);
    }
}
