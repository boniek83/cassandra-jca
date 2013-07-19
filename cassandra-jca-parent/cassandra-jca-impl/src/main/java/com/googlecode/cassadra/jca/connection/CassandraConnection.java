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
package com.googlecode.cassadra.jca.connection;

import org.apache.cassandra.thrift.Cassandra;

import com.googlecode.cassadra.jca.managed.connection.CassandraIfaceWrapper;
import com.googlecode.cassadra.jca.managed.connection.ConnectionProvider;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jun 24, 2013
 */
public class CassandraConnection implements com.googlecode.cassadra.jca.api.CassandraConnection {

    private final ConnectionProvider mcf;
    private CassandraIfaceWrapper iface = null;

    public CassandraConnection(ConnectionProvider mcf) {
        this.mcf = mcf;
    }

    public Cassandra.Iface getInternalConnection() {
        if (iface == null) {
            iface = mcf.getInternalConnection();
        }
        return iface;
    }

    public void close() {
        if (iface != null) {
            iface.setIface(new ClosedCassandraIface());
        }
        mcf.close();
    }
}
