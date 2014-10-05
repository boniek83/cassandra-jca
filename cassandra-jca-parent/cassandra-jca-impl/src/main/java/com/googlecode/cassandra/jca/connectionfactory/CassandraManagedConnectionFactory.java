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
package com.googlecode.cassandra.jca.connectionfactory;

import java.io.PrintWriter;
import java.util.Set;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.resource.spi.ValidatingManagedConnectionFactory;
import javax.security.auth.Subject;


import com.googlecode.cassandra.jca.managed.connection.CassandraManagedConnection;

/**
 *
 * @author sergey.sarabun@gmail.com
 * @date Apr 21, 2013
 */
public class CassandraManagedConnectionFactory
        extends CassandraProperties
        implements ManagedConnectionFactory,
        ValidatingManagedConnectionFactory,
        ResourceAdapterAssociation {

    private static final long serialVersionUID = 1L;
    private PrintWriter out;
    private ResourceAdapter ra;

    public com.googlecode.cassandra.jca.api.CassandraConnectionFactory createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
        return new CassandraConnectionFactory(this, cxManager);
    }

    public Object createConnectionFactory() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        return new CassandraManagedConnection(this);
    }

    public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        ManagedConnection result = null;
        for (Object object : connectionSet) {
            if (object instanceof CassandraManagedConnection) {
                result = (ManagedConnection) object;
                break;
            }
        }
        return result;
    }

    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.out = out;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        return out;
    }

    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
        this.ra = ra;
    }

    public Set getInvalidConnections(Set connectionSet) throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEquals = true;
        if (obj != this) {
            if (obj == null) {
                isEquals = false;
            } else {
                if (!(obj instanceof CassandraManagedConnectionFactory)) {
                    isEquals = false;
                }
            }
        }

        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.ra != null ? this.ra.hashCode() : 0);
        return hash;
    }
}
