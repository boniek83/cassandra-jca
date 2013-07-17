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
package com.ssarabun.jca.cassandra.connection;


import com.ssarabun.jca.cassandra.managed.connection.CassandraIfaceWrapper;
import org.apache.cassandra.thrift.Cassandra;

import com.ssarabun.jca.cassandra.managed.connection.NewInterface;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jun 24, 2013
 */
public class CassandraConnection implements com.ssarabun.jca.cassandra.api.CassandraConnection {

    private final NewInterface mcf;
    private CassandraIfaceWrapper iface = null;

    public CassandraConnection(NewInterface mcf) {
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
