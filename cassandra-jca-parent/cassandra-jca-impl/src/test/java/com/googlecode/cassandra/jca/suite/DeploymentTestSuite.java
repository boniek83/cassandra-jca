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
package com.googlecode.cassandra.jca.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.googlecode.cassandra.jca.connectionfactory.CassandraJCATest;
import org.apache.cassandra.service.CassandraDaemon;

/**
 *
 * @author sergey.sarabun@gmail.com
 * @date Jul 17, 2013
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CassandraJCATest.class
})
public class DeploymentTestSuite {

    private static CassandraDaemon cassandraDaemon = null;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("public static void beforeClass()");
        cassandraDaemon = new CassandraDaemon();
        cassandraDaemon.init(null);
        cassandraDaemon.start();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("public static void afterClass()");
        if (cassandraDaemon != null) {
            cassandraDaemon.stop();
        }
    }
}
