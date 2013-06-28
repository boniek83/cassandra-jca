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

import java.util.HashMap;
import java.util.Map;
import org.apache.cassandra.thrift.AuthenticationException;
import org.apache.cassandra.thrift.AuthenticationRequest;
import org.apache.cassandra.thrift.AuthorizationException;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jun 24, 2013
 */
public class CassandraConnection implements com.ssarabun.jca.cassandra.api.CassandraConnection {

    private Cassandra.Iface ifacel;
    private TFramedTransport transport;

    public CassandraConnection(CassandraProperties properties)
            throws InvalidRequestException,
            TException,
            AuthenticationException,
            AuthorizationException {

        transport = new TFramedTransport(new TSocket(
                properties.getServer(),
                properties.getPort(),
                properties.getTimeout()));

        TProtocol framedProtocol = new TBinaryProtocol(transport);

        ifacel = new Cassandra.Client(framedProtocol);

        transport.open();

        if (StringUtils.isNotBlank(properties.getUsername())
                && StringUtils.isNotBlank(properties.getPassword())) {
            
            Map<String, String> credentials = new HashMap<String, String>();
            //TODO
            AuthenticationRequest request = new AuthenticationRequest(credentials);
            request.validate();
            ifacel.login(request);
        }

        if (StringUtils.isNotBlank(properties.getKeyspace())) {
            ifacel.set_keyspace(properties.getKeyspace());
        }
    }

    public Cassandra.Iface getInternalConnection() {
        return ifacel;
    }

    protected void close() {
        transport.close();
    }
}
