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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Apr 29, 2013
 */
public class CassandraManagedConnection implements ManagedConnection {

    private static final Logger logger = LoggerFactory.getLogger(CassandraManagedConnection.class);
    private PrintWriter out;
    private List<ConnectionEventListener> listeners = new ArrayList<ConnectionEventListener>();
    private CassandraManagedConnectionFactory cmcf;
    //
    private CassandraConnection internalConnection;

    public CassandraManagedConnection(CassandraManagedConnectionFactory cmcf) {
        this.cmcf = cmcf;
    }

    public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {

        if (internalConnection == null) {
            try {
                internalConnection = new CassandraConnection(cmcf);
            } catch (Exception ex) {
                throw new ResourceException(ex);
            }
        }

        return internalConnection;
    }

    public void destroy() throws ResourceException {
        if (internalConnection != null) {
            internalConnection.close();
            internalConnection = null;
        }
    }

    public void cleanup() throws ResourceException {
        if (internalConnection != null) {
            internalConnection.close();
            internalConnection = null;
        }
    }

    public void associateConnection(Object connection) throws ResourceException {
        if (connection instanceof CassandraConnection) {
            internalConnection = (CassandraConnection) connection;
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
