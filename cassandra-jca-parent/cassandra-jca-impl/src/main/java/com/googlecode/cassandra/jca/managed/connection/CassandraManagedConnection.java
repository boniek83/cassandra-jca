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
package com.googlecode.cassandra.jca.managed.connection;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import org.apache.cassandra.thrift.AuthenticationRequest;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.cassandra.jca.connection.CassandraConnection;
import com.googlecode.cassandra.jca.connection.factory.CassandraProperties;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Apr 29, 2013
 */
public class CassandraManagedConnection implements ManagedConnection, ConnectionProvider {

    private static final Logger logger = LoggerFactory.getLogger(CassandraManagedConnection.class);
    private PrintWriter out;
    private List<ConnectionEventListener> listeners = new ArrayList<ConnectionEventListener>();
    //
    private CassandraConnection cassandraConnection;
    private Cassandra.Iface iface;
    private TFramedTransport transport;

    public CassandraManagedConnection(CassandraProperties properties) throws ResourceException {

        try {
            transport = new TFramedTransport(new TSocket(
                    properties.getServer(),
                    properties.getPort(),
                    properties.getTimeout()));

            TProtocol framedProtocol = new TBinaryProtocol(transport);

            iface = new Cassandra.Client(framedProtocol);

            transport.open();

            if (StringUtils.isNotBlank(properties.getUsername())
                    && StringUtils.isNotBlank(properties.getPassword())) {

                Map<String, String> credentials = new HashMap<String, String>();
                //TODO
                AuthenticationRequest request = new AuthenticationRequest(credentials);
                request.validate();
                iface.login(request);
            }

            if (StringUtils.isNotBlank(properties.getKeyspace())) {
                iface.set_keyspace(properties.getKeyspace());
            }
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }
    }

    @Override
    public CassandraIfaceWrapper getInternalConnection() {
        return new CassandraIfaceWrapper(iface);
    }

    @Override
    public void close() {
//        logger.info("void close()");
//        logger.info("this = " + this);
        ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
        event.setConnectionHandle(cassandraConnection);

        for (ConnectionEventListener cel : listeners) {
            cel.connectionClosed(event);
        }
    }

    public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
//        logger.info("public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo)");
//        logger.info("this = " + this);
        cassandraConnection = new CassandraConnection(this);
//        logger.info("cassandraConnection = " + cassandraConnection);
        return cassandraConnection;
    }

    public void destroy() throws ResourceException {
//        logger.info("public void destroy()");
        if (transport != null) {
            transport.close();
            transport = null;
            iface = null;
        }
    }

    public void cleanup() throws ResourceException {
//        logger.info("public void cleanup()");
//        logger.info("this = " + this);
        cassandraConnection = null;
    }

    public void associateConnection(Object connection) throws ResourceException {
//        logger.info("public void associateConnection(Object connection)");
//        logger.info("connection = " + connection);
        if (connection instanceof CassandraConnection) {
            cassandraConnection = (CassandraConnection) connection;
        }
    }

    public void addConnectionEventListener(ConnectionEventListener listener) {
        listeners.add(listener);
    }

    public void removeConnectionEventListener(ConnectionEventListener listener) {
        listeners.remove(listener);
    }

    public XAResource getXAResource() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LocalTransaction getLocalTransaction() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ManagedConnectionMetaData getMetaData() throws ResourceException {
        return new CassandraManagedConnectionMetaData();
    }

    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.out = out;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        return out;
    }
}
