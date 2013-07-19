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
package com.googlecode.cassadra.jca.connection.factory;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.cassandra.thrift.KsDef;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.ResourceAdapterArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.googlecode.cassadra.jca.api.CassandraConnection;
import com.googlecode.cassadra.jca.api.CassandraConnectionFactory;
import com.googlecode.cassadra.jca.connection.ClosedCassandraIface;
import com.googlecode.cassadra.jca.connection.exception.ClosedCassandraIfaceException;
import com.googlecode.cassadra.jca.managed.connection.CassandraManagedConnection;
import com.googlecode.cassadra.jca.ra.CassandraResourceAdapter;

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
        ResourceAdapterArchive rar =
                ShrinkWrap.create(ResourceAdapterArchive.class, deploymentName + ".rar");
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class,
                UUID.randomUUID().toString() + ".jar");
        jar.addPackage(CassandraResourceAdapter.class.getPackage());
        jar.addPackage(ClosedCassandraIface.class.getPackage());
        jar.addPackage(ClosedCassandraIfaceException.class.getPackage());
        jar.addPackage(CassandraManagedConnectionFactory.class.getPackage());
        jar.addPackage(CassandraManagedConnection.class.getPackage());
        rar.addAsLibrary(jar);
        rar.addAsManifestResource("META-INF/ironjacamar.xml", "ironjacamar.xml");
        rar.addAsManifestResource("META-INF/ra.xml", "ra.xml");

        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        resolver.artifact("com.googlecode.cassadra.jca:cassandra-jca-api");
        rar.addAsLibraries(resolver.resolveAsFiles());

        return rar;
    }
    /** resource */
    @Resource(mappedName = "java:/eis/CassandraConnectionFactory")
    private CassandraConnectionFactory cf;

    @Test
    public void testLookupConnectionFactory() throws Throwable {
        assertNotNull(cf);

        CassandraConnection c = cf.getConnection();
        assertNotNull(c);

        List<KsDef> list = c.getInternalConnection().describe_keyspaces();
        assertNotNull(list);
        assertFalse(list.isEmpty());

        c.close();
    }
}
